package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreateUserDTO;
import com.bluefox.Pizzeria.interfaces.IPersonRepository;
import com.bluefox.Pizzeria.model.people.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final IPersonRepository repository;

    public UserService(@Qualifier("personArrayList") IPersonRepository repository) {
        this.repository = repository;
    }

    public Client createUser(CreateUserDTO dto) throws IllegalArgumentException, IllegalStateException {
        Client client = Client.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .address(dto.getAddress())
                .birthday(dto.getBirthday())
                .build();

        repository.save(client);
        return client;
    }

    public Client getUserById(UUID id) {
        return (Client) repository.findById(id);
    }
}
