package com.bluefox.Pizzeria.interfaces;

import com.bluefox.Pizzeria.model.food.Food;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public interface IFoodRepository extends IRepository<Food> {
    List<Food> findByName(String name) throws IllegalArgumentException, NoSuchElementException;
    List<Food> findByType(Class<?> clazz) throws IllegalArgumentException, NoSuchElementException;
    List<Food> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) throws IllegalArgumentException, NoSuchElementException;
    List<Food> findByNotAvailable() throws IllegalArgumentException, NoSuchElementException;
    List<Food> findByAvailable() throws IllegalArgumentException, NoSuchElementException;
}
