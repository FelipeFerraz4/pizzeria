package com.bluefox.Pizzeria.interfaces;

import com.bluefox.Pizzeria.model.people.Person;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface IPersonRepository extends IRepository<Person> {
    Optional<Person> findByEmail(String email) throws IllegalArgumentException, NoSuchElementException;
    Optional<Person> findByPhoneNumber(String phoneNumber) throws IllegalArgumentException, NoSuchElementException;
    List<Person> findByNameIgnoreCase(String username) throws IllegalArgumentException, NoSuchElementException;
    List<Person> findByType(Class<?> clazz) throws IllegalArgumentException, NoSuchElementException;
}
