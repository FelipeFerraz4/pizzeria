package com.bluefox.Pizzeria.model.order;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum OrderStatus {
    PENDING("Pendente"),
    IN_PREPARATION("Em preparação"),
    OUT_FOR_DELIVERY("Saiu para entrega"),
    DELIVERED("Entregue"),
    CANCELED("Cancelado");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public static OrderStatus fromPortuguese(String pt) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.status.equalsIgnoreCase(pt.trim())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status desconhecido em português: " + pt);
    }

    @Override
    public String toString() {
        return this.name();
    }
}
