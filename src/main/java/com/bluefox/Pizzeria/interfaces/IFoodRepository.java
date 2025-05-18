package com.bluefox.Pizzeria.interfaces;

import com.bluefox.Pizzeria.model.food.Food;

import java.util.List;
import java.util.NoSuchElementException;

public interface IFoodRepository extends IRepository<Food> {
    Food findByName(String name) throws IllegalArgumentException, NoSuchElementException;
    List<Food> findByType(Class<?> clazz) throws IllegalArgumentException, NoSuchElementException;
    List<Food> findBypriceRange(double minPrice, double maxPrice) throws IllegalArgumentException, NoSuchElementException;
    List<Food> findByNotAvailable() throws IllegalArgumentException, NoSuchElementException;
}
