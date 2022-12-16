package org.microservices.store.service.impl;

import org.microservices.store.domain.Notification;
import org.microservices.store.domain.Product;
import org.microservices.store.dto.ProductDTO;
import org.microservices.store.exception.ResourceNotFoundException;
import org.microservices.store.mapper.ProductMapper;
import org.microservices.store.repository.ProductRepository;
import org.microservices.store.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final KafkaTemplate kafkaTemplate;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(KafkaTemplate kafkaTemplate, ProductRepository productRepository, ProductMapper productMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Set<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(productMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    @Cacheable(cacheNames = "productCache", key = "#id")
    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = productRepository.save(productMapper.toEntity(productDTO));
        Notification notification =  Notification.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .message("Product has been added")
                .localDateTime(LocalDateTime.now())
                .build();
        kafkaTemplate.send("notification",notification);
        return productMapper.toDTO(product);

    }

    @Override
    @Transactional
    @CachePut(cacheNames = "productCache", key = "#id")
    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product productFromDB = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        BeanUtils.copyProperties(productMapper.toEntity(productDTO), productFromDB, "id");
        Product product = productRepository.save(productFromDB);
        return productMapper.toDTO(product);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "productCache", key = "#id")
    public void delete(Long id) {
        Product productFromDB = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        productRepository.delete(productFromDB);
    }
}
