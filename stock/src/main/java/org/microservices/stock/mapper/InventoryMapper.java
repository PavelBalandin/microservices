package org.microservices.stock.mapper;

import org.microservices.stock.domain.Inventory;
import org.microservices.stock.dto.InventoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {

    private final ModelMapper modelMapper;

    public InventoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InventoryResponse toInventoryResponse(Inventory inventory) {
        return modelMapper.map(inventory, InventoryResponse.class);
    }
}
