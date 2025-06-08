package com.bluefox.Pizzeria.repository.food;

import com.bluefox.Pizzeria.model.food.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository("pizzaRepositoryJPA")
public interface IPizzaRepository extends JpaRepository<Pizza, UUID> {

    List<Pizza> findByNameIgnoreCase(String name);

    List<Pizza> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Pizza> findByAvailableFalse();

    List<Pizza> findByAvailableTrue();
}
