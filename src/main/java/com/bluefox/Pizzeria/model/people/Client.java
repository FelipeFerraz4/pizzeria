package com.bluefox.Pizzeria.model.people;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Client class represents a client, extending the Person class, with customer-specific attributes.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class Client extends Person {

    private LocalDate birthday;
    @Builder.Default
    private LocalDate dateLastPurchase = null;
    @Builder.Default
    private List<UUID> orders = new ArrayList<>();
    @Builder.Default
    private int loyaltyPoints = 0;
    @Builder.Default
    private boolean vip = false;
    @Builder.Default
    private String preferredPaymentMethod = "";
    @Builder.Default
    private String notes = "";
}
