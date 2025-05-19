package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreateClientDTO;
import com.bluefox.Pizzeria.dtos.UpdateClientDTO;
import com.bluefox.Pizzeria.interfaces.IPersonRepository;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.model.people.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

    public Client updateClient(UUID id, UpdateClientDTO dto) throws IllegalArgumentException, NoSuchElementException {
        Client client = (Client) repository.findById(id);
        client.setName(dto.name());
        client.setPhoneNumber(dto.phoneNumber());
        client.setPassword(dto.password());
        client.setAddress(dto.address());
        client.setNotes(dto.notes());
        repository.updateById(client);
        return client;
    }

    public void deleteUserById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        repository.deleteByID(id);
    }

    public Client getUserByEmail(String email) throws IllegalArgumentException, NoSuchElementException {
        return (Client) repository.findByEmail(email);
    }

    public Client getUserByPhoneNumber(String phoneNumber) throws IllegalArgumentException, NoSuchElementException {
        return (Client) repository.findByPhoneNumber(phoneNumber);
    }

    public List<Person> getUserByName(String name) throws IllegalArgumentException, NoSuchElementException {
        return repository.findByName(name);
    }

    public List<Client> getClients() throws IllegalArgumentException, NoSuchElementException {
        return repository.findByType(Client.class)
                .stream()
                .map(Client.class::cast)
                .toList();
    }

    public List<Person> getAllUsers() {
        return repository.findAll();
    }

}
