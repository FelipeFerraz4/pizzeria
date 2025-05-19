package com.bluefox.Pizzeria.dtos;

import java.math.BigDecimal;
import java.util.List;

public record UpdatePizzaDTO(
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        List<String> ingredients
) {
}
