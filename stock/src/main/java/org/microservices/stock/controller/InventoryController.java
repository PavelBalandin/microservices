package org.microservices.stock.controller;

import org.microservices.stock.dto.InventoryResponse;
import org.microservices.stock.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{skuCode}")
    public ResponseEntity<InventoryResponse> getById(@PathVariable("skuCode") Long skuCode) {
        return new ResponseEntity<>(inventoryService.getBySkuCode(skuCode), HttpStatus.OK);
    }
}
