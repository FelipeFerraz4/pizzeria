package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreateClientDTO;
import com.bluefox.Pizzeria.dtos.UpdateClientDTO;
import com.bluefox.Pizzeria.interfaces.IPersonRepository;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.model.people.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final IPersonRepository repository;

    public UserService(@Qualifier("personArrayList") IPersonRepository repository) {
        this.repository = repository;
    }

    public Client createUser(CreateClientDTO dto) throws IllegalArgumentException, IllegalStateException {
        Client client = Client.builder()
                .name(dto.name())
                .phoneNumber(dto.phoneNumber())
                .email(dto.email())
                .password(dto.password())
                .address(dto.address())
                .birthday(dto.birthday())
                .build();

        repository.save(client);
        return client;
    }

    public Client getUserById(UUID id) {
        return (Client) repository.findById(id);
    }

    public Client getUserByEmail(String email) {
        return (Client) repository.findByEmail(email);
    }

    public Client getUserByPhoneNumber(String phoneNumber) {
        return (Client) repository.findByPhoneNumber(phoneNumber);
    }

    public List<Person> getUserByName(String name) {
        return repository.findByName(name);
    }

    public List<Person> getUserByType(Class<?> clazz) {
        return repository.findByType(clazz);
    }

    public List<Person> getAllUsers() {
        return repository.findAll();
    }

    public void deleteUserById(UUID id) {
        repository.deleteByID(id);
    }

    public void updateUserById(UUID id, UpdateClientDTO dto) {
        Client client = (Client) repository.findById(id);
        client.setName(dto.name());
        client.setPhoneNumber(dto.phoneNumber());
        client.setPassword(dto.password());
        client.setAddress(dto.address());
        client.setNotes(dto.notes());
        repository.updateById(client);
    }
}
