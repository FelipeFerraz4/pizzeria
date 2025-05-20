package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreatePizzaDTO;
import com.bluefox.Pizzeria.dtos.UpdatePizzaDTO;
import com.bluefox.Pizzeria.interfaces.IFoodRepository;
import com.bluefox.Pizzeria.model.food.Food;
import com.bluefox.Pizzeria.model.food.Pizza;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FoodService {
    private final IFoodRepository foodRepository;

    public FoodService(@Qualifier("foodArrayList") IFoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Pizza createFood(CreatePizzaDTO dto) throws IllegalArgumentException, IllegalStateException {
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
                .build();


        foodRepository.save(pizza);
        return pizza;
    }

    public Food getFoodById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        return foodRepository.findById(id);
    }

    public Pizza getPizzaById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        return (Pizza) foodRepository.findById(id);
    }

    public Pizza updatePizza(UUID id, UpdatePizzaDTO dto) throws IllegalArgumentException, NoSuchElementException {
        Pizza pizza = (Pizza) foodRepository.findById(id);
        pizza.setName(dto.name());
        pizza.setDescription(dto.description());
        pizza.setPrice(dto.price());
        pizza.setIngredients(dto.ingredients());
        pizza.setImageUrl(dto.imageUrl());
        foodRepository.updateById(pizza);
        return pizza;
    }

    public void deleteFoodById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        foodRepository.deleteByID(id);
    }

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public List<Food> getFoodByName(String name) throws IllegalArgumentException, NoSuchElementException {
        return foodRepository.findByName(name);
    }

    public List<Food> getFoodByType(Class<?> clazz) throws IllegalArgumentException, NoSuchElementException {
        return foodRepository.findByType(clazz);
    }

    public List<Pizza> getPizzas() throws IllegalArgumentException, NoSuchElementException {
        return foodRepository.findByType(Pizza.class).stream()
                .map(Pizza.class::cast)
                .toList();
    }

    public List<Food> getFoodByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) throws IllegalArgumentException, NoSuchElementException {
        return foodRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Food> getUnavailableFood() throws IllegalArgumentException, NoSuchElementException {
        return foodRepository.findByNotAvailable();
    }

    public List<Food> getAvailableFood() throws IllegalArgumentException, NoSuchElementException {
        return foodRepository.findByAvailable();
    }
}
