package com.bluefox.Pizzeria.dtos;

import com.bluefox.Pizzeria.model.order.OrderItem;

import java.util.List;

public record CreateOrderDTO(
        String clientId,
        List<OrderItem> items,
        String deliveryAddress,
        String paymentMethod,
        String notes
) {
}
