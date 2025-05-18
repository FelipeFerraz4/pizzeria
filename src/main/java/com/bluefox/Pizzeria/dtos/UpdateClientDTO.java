package com.bluefox.Pizzeria.dtos;

import java.time.LocalDate;

public record UpdateClientDTO(
        String name,
        String phoneNumber,
        String password,
        String address,
        String notes
) {
}
