package org.microservices.stock.service;

import org.microservices.stock.domain.Inventory;
import org.microservices.stock.dto.InventoryResponse;

public interface InventoryService {
    public InventoryResponse getBySkuCode(Long skuCode);
}
