package com.bluefox.Pizzeria.model.food;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents a generic food item with basic properties.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Food {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String name;
    private String description;
    private BigDecimal price;
    @Builder.Default
    private boolean available = true;
    private String category;
}
