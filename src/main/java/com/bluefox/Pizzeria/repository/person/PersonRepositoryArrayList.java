package com.bluefox.Pizzeria.repository.person;

import com.bluefox.Pizzeria.interfaces.IPersonRepository;
import com.bluefox.Pizzeria.model.people.Person;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("personArrayList")
public class PersonRepositoryArrayList implements IPersonRepository {

    private final List<Person> people = new ArrayList<>();

    @Override
    public Optional<Person> save(Person object) throws IllegalArgumentException, IllegalStateException {
        if (object == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula.");
        }

        boolean exists = people.stream()
                .anyMatch(p -> p.getId().equals(object.getId()));

        if (exists) {
            throw new IllegalStateException("Pessoa com ID '" + object.getId() + "' já existe.");
        }

        boolean emailExists = people.stream()
                .anyMatch(p -> p.getEmail().equalsIgnoreCase(object.getEmail()));
        if (emailExists) {
            throw new IllegalStateException("Email '" + object.getEmail() + "' já está em uso.");
        }

        boolean phoneNumberExists = people.stream()
                .anyMatch(p -> p.getPhoneNumber().equals(object.getPhoneNumber()));
        if (phoneNumberExists) {
            throw new IllegalStateException("Número de telefone '" + object.getPhoneNumber() + "' já está em uso.");
        }

        people.add(object);
        return Optional.of(object);
    }

    @Override
    public Optional<Person> findById(UUID id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return people.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst();
    }

    @Override
    public List<Person> findByNameIgnoreCase(String username) throws IllegalArgumentException {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser nulo ou vazio.");
        }

        return people.stream()
                .filter(Person::isActive)
                .filter(p -> p.getName().toLowerCase().contains(username.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findByEmail(String email) throws IllegalArgumentException {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio.");
        }

        return people.stream()
                .filter(p -> email.equalsIgnoreCase(p.getEmail()))
                .findFirst();
    }

    @Override
    public Optional<Person> findByPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Número de telefone não pode ser nulo ou vazio.");
        }

        return people.stream()
                .filter(p -> phoneNumber.equals(p.getPhoneNumber()))
                .findFirst();
    }

    @Override
    public void updateById(Person object) throws IllegalArgumentException, NoSuchElementException {
        if (object == null || object.getId() == null) {
            throw new IllegalArgumentException("Pessoa ou ID não pode ser nulo.");
        }

        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId().equals(object.getId())) {
                people.set(i, object);
                return;
            }
        }

        throw new NoSuchElementException("Pessoa com ID '" + object.getId() + "' não encontrada para atualização.");
    }

    @Override
    public void deleteByID(UUID id) throws IllegalArgumentException, NoSuchElementException, IllegalStateException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Person person = people.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Pessoa com ID '" + id + "' não encontrada."));

        if (!person.isActive()) {
            throw new IllegalStateException("Pessoa com ID '" + id + "' já está inativa.");
        }

        person.setActive(false);
    }

    @Override
    public List<Person> findByType(Class<?> clazz) throws IllegalArgumentException {
        if (clazz == null) {
            throw new IllegalArgumentException("Classe não pode ser nula.");
        }

        return people.stream()
                .filter(Person::isActive)
                .filter(clazz::isInstance)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(people);
    }
}
