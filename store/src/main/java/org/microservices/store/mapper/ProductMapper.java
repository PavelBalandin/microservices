package org.microservices.store.mapper;

import org.microservices.store.domain.Product;
import org.microservices.store.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    private final ModelMapper modelMapper;
    
    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO toDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
