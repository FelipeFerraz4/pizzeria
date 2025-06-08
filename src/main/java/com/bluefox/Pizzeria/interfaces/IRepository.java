package com.bluefox.Pizzeria.interfaces;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public interface IRepository<T> {
    Optional<T> save(T object) throws IllegalArgumentException, IllegalStateException;
    Optional<T> findById(UUID id) throws IllegalArgumentException;
    void updateById(T object) throws IllegalArgumentException, NoSuchElementException;
    void deleteByID(UUID id) throws IllegalArgumentException, NoSuchElementException;
    List<T> findAll();
}
