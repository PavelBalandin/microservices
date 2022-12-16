package org.microservices.store.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private String productName;
    private BigDecimal price;
    private String message;
    private LocalDateTime localDateTime;
}
