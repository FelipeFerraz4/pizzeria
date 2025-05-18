package com.bluefox.Pizzeria.model.people;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Person class represents a person with common user attributes.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Person {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    @Builder.Default
    private String role = "client";
    @Builder.Default
    private boolean active = true;
    @Builder.Default
    private LocalDateTime accountCreationDate = LocalDateTime.now();
}
