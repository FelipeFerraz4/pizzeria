package com.bluefox.Pizzeria.dtos;

import java.time.LocalDate;

public record CreateClientDTO(
        String name,
        String phoneNumber,
        String email,
        String password,
        String address,
        LocalDate birthday
){}
