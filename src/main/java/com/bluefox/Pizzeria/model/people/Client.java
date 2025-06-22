package com.bluefox.Pizzeria.model.people;

import com.bluefox.Pizzeria.model.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Client class represents a client, extending the Person class, with customer-specific attributes.
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "clients")
public class Client extends Person {

    private LocalDate birthday;

    private LocalDateTime dateLastPurchase;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    private int loyaltyPoints;

    private boolean vip;

    private String preferredPaymentMethod;

    private String notes;

    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setClient(null);
    }
}
