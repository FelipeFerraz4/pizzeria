package com.bluefox.Pizzeria.interfaces;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface IRepository<T> {
    void save(T object) throws IllegalArgumentException, IllegalStateException;
    T findById(UUID id) throws IllegalArgumentException, NoSuchElementException;
    void updateById(T object) throws IllegalArgumentException, NoSuchElementException;
    void deleteByID(UUID id) throws IllegalArgumentException, NoSuchElementException;
    List<T> findAll();
}
