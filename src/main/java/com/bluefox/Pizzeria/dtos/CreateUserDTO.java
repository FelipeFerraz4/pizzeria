package com.bluefox.Pizzeria.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    private LocalDate birthday;
}
