package com.bluefox.Pizzeria.model.food;

import jakarta.persistence.*;
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
@Entity
@Table(name = "pizza")
public class Pizza extends Food {

    @Enumerated(EnumType.STRING)
    private Size size;

    @ElementCollection
    @CollectionTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id"))
    @Column(name = "ingredient")
    private List<String> ingredients = new ArrayList<>();

    @Column(name = "crust_flavor")
    private String crustFlavor;

    private boolean vegetarian;

    private boolean spicy;
}