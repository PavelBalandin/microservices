package org.microservices.stock.service.impl;

import org.microservices.stock.domain.Inventory;
import org.microservices.stock.dto.InventoryResponse;
import org.microservices.stock.exception.ResourceNotFoundException;
import org.microservices.stock.mapper.InventoryMapper;
import org.microservices.stock.repository.InventoryRepository;
import org.microservices.stock.service.InventoryService;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public InventoryResponse getBySkuCode(Long skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(ResourceNotFoundException::new);
        return  inventoryMapper.toInventoryResponse(inventory);
    }
}
