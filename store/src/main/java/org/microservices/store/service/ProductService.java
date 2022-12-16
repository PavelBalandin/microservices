package org.microservices.store.service;

import org.microservices.store.dto.ProductDTO;

import java.util.Set;

public interface ProductService {

    Set<ProductDTO> getAll();

    ProductDTO getById(Long id);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO, Long id);

    void delete(Long id);

}
