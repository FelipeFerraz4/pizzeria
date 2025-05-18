package com.bluefox.Pizzeria.model.order;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents a customer's order, containing a list of food items and order-related metadata.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@SuperBuilder
public class Order {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private UUID clientId;
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private String deliveryAddress;
    private String paymentMethod;
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;
    private BigDecimal totalPrice;
    private String notes;
    private UUID deliveryPersonId;
}
