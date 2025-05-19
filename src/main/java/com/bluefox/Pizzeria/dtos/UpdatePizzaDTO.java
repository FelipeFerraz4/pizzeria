package com.bluefox.Pizzeria.dtos;

import com.bluefox.Pizzeria.model.food.Size;

import java.util.List;

public record UpdatePizzaDTO(
        String name,
        String description,
        Double price,
        String imageUrl,
        List<String> ingredients
) {
}
