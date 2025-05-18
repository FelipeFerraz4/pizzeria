package com.bluefox.Pizzeria.interfaces;

import com.bluefox.Pizzeria.model.people.Person;

import java.util.List;
import java.util.NoSuchElementException;

public interface IPersonRepository extends IRepository<Person> {
    Person findByEmail(String email) throws IllegalArgumentException, NoSuchElementException;
    Person findByPhoneNumber(String phoneNumber) throws IllegalArgumentException, NoSuchElementException;
    List<Person> findByName(String username) throws IllegalArgumentException, NoSuchElementException;
    List<Person> findByType(Class<?> clazz) throws IllegalArgumentException, NoSuchElementException;
}
