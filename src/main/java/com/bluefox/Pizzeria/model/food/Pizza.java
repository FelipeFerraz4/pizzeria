package com.bluefox.Pizzeria.model.food;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a specific food item: Pizza, with additional properties.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class Pizza extends Food {

    private Size size;
    @Builder.Default
    private List<String> ingredients = new ArrayList<>();
    @Builder.Default
    private String crustFlavor = "Normal";
    private boolean vegetarian;
    private boolean spicy;
}
