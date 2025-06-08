package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreatePizzaDTO;
import com.bluefox.Pizzeria.dtos.UpdatePizzaDTO;
import com.bluefox.Pizzeria.model.food.Pizza;
import com.bluefox.Pizzeria.repository.food.IPizzaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
public class PizzaService {

    private final IPizzaRepository repository;

    public PizzaService(@Qualifier("pizzaRepositoryJPA") IPizzaRepository repository) {
        this.repository = repository;
    }

    public Pizza createPizza(CreatePizzaDTO dto) throws IllegalArgumentException {
        Pizza pizza = Pizza.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .ingredients(dto.ingredients())
                .imageUrl(dto.imageUrl())
                .category(dto.category())
                .size(dto.size())
                .vegetarian(dto.vegetarian())
                .spicy(dto.spicy())
                .crustFlavor(dto.crustFlavor())
                .available(true)
                .build();

        return repository.saveAndFlush(pizza);
    }

    public Pizza getPizzaById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pizza with ID '" + id + "' not found."));
    }

    public Pizza updatePizza(UUID id, UpdatePizzaDTO dto) throws IllegalArgumentException, NoSuchElementException {
        Pizza pizza = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pizza with ID '" + id + "' not found."));

        if (dto.name() != null && !dto.name().isBlank()) {
            pizza.setName(dto.name());
        }
        if (dto.description() != null && !dto.description().isBlank()) {
            pizza.setDescription(dto.description());
        }
        if (dto.price() != null) {
            pizza.setPrice(dto.price());
        }
        if (dto.ingredients() != null && !dto.ingredients().isEmpty()) {
            pizza.setIngredients(dto.ingredients());
        }
        if (dto.imageUrl() != null && !dto.imageUrl().isBlank()) {
            pizza.setImageUrl(dto.imageUrl());
        }

        return repository.saveAndFlush(pizza);
    }

    public void deletePizzaById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Pizza with ID '" + id + "' not found.");
        }

        repository.deleteById(id);
    }

    public List<Pizza> getAllPizza() {
        return repository.findAll();
    }

    public List<Pizza> getPizzaByName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        return repository.findByNameIgnoreCase(name);
    }

    public List<Pizza> getPizzas() {
        return repository.findAll();
    }

    public List<Pizza> getPizzaByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Pizza> getUnavailablePizza() {
        return repository.findByAvailableFalse();
    }

    public List<Pizza> getAvailablePizza() {
        return repository.findByAvailableTrue();
    }
}
