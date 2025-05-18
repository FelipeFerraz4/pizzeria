package com.bluefox.Pizzeria.model.order;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents an item in a customer's order.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderItem {

    private UUID foodId;
    private String foodName;
    private int quantity;
    private BigDecimal unitPrice;

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
