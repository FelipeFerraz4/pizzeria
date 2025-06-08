package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreateClientDTO;
import com.bluefox.Pizzeria.dtos.UpdateClientDTO;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.repository.person.IClientRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ClientService {

    private final IClientRepository repository;

    public ClientService(@Qualifier("clientRepositoryJPA") IClientRepository repository) {
        this.repository = repository;
    }

    public Client createClient(CreateClientDTO dto) {
        Client client = Client.builder()
                .name(dto.name())
                .phoneNumber(dto.phoneNumber())
                .email(dto.email())
                .password(dto.password())
                .address(dto.address())
                .birthday(dto.birthday())
                .build();

        return repository.saveAndFlush(client);
    }

    public Client getClientById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com ID '" + id + "' não encontrado."));
    }

    public Client updateClient(UUID id, UpdateClientDTO dto) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com ID '" + id + "' não encontrado."));

        if (dto.name() != null && !dto.name().isBlank()) {
            client.setName(dto.name());
        }
        if (dto.phoneNumber() != null && !dto.phoneNumber().isBlank()) {
            client.setPhoneNumber(dto.phoneNumber());
        }
        if (dto.password() != null && !dto.password().isBlank()) {
            client.setPassword(dto.password());
        }
        if (dto.address() != null && !dto.address().isBlank()) {
            client.setAddress(dto.address());
        }
        if (dto.notes() != null && !dto.notes().isBlank()) {
            client.setNotes(dto.notes());
        }

        return repository.saveAndFlush(client);
    }

    public void deleteClientById(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Cliente com ID '" + id + "' não encontrado.");
        }
        repository.deleteById(id);
    }

    public Client getClientByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Cliente com email '" + email + "' não encontrado."));
    }

    public Client getClientsByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NoSuchElementException("Cliente com número de telefone '" + phoneNumber + "' não encontrado."));
    }

    public List<Client> getClientsByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }
}
