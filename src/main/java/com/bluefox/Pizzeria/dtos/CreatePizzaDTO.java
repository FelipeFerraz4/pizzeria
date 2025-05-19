package com.bluefox.Pizzeria.dtos;

import com.bluefox.Pizzeria.model.food.Size;

import java.math.BigDecimal;
import java.util.List;

public record CreatePizzaDTO(
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        String category,
        Size size,
        Boolean vegetarian,
        Boolean spicy,
        List<String> ingredients,
        String crustFlavor
) {
}
