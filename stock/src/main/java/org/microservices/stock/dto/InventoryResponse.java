package org.microservices.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponse {
    private Long skuCode;
    private Long quantity;
}
