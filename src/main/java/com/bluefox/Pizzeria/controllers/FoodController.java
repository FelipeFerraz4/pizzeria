package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.dtos.CreatePizzaDTO;
import com.bluefox.Pizzeria.dtos.UpdatePizzaDTO;
import com.bluefox.Pizzeria.model.food.Food;
import com.bluefox.Pizzeria.model.food.Pizza;
import com.bluefox.Pizzeria.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createFood(@RequestBody CreatePizzaDTO dto) {
        try {
            Pizza pizza = foodService.createFood(dto);
            URI location = URI.create("/food/" + pizza.getId());
            return ResponseEntity.created(location).body(ApiResponse.success(pizza));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Dados inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllFoods() {
        try {
            List<Food> foods = foodService.getAllFood();
            return ResponseEntity.ok(ApiResponse.successWithCount(foods, foods.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getFoodById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Food food = foodService.getFoodById(uuid);
            return ResponseEntity.ok(ApiResponse.success(food));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<?>> getFoodByName(@PathVariable String name) {
        try {
            List<Food> foods = foodService.getFoodByName(name);
            return ResponseEntity.ok(ApiResponse.successWithCount(foods, foods.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Nome inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateFood(@PathVariable String id, @RequestBody UpdatePizzaDTO dto) {
        try {
            UUID uuid = UUID.fromString(id);
            Food food = foodService.updatePizza(uuid, dto);
            return ResponseEntity.ok(ApiResponse.success(food));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteFood(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            foodService.deleteFoodById(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse<?>> getAvailableFoods() {
        try {
            List<Food> foods = foodService.getAvailableFood();
            return ResponseEntity.ok(ApiResponse.successWithCount(foods, foods.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/unavailable")
    public ResponseEntity<ApiResponse<?>> getUnavailableFoods() {
        try {
            List<Food> foods = foodService.getUnavailableFood();
            return ResponseEntity.ok(ApiResponse.successWithCount(foods, foods.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/pizza")
    public ResponseEntity<ApiResponse<?>> getAllPizzas() {
        try {
            List<Pizza> pizzas = foodService.getPizzas();
            return ResponseEntity.ok(ApiResponse.successWithCount(pizzas, pizzas.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<ApiResponse<?>> getPizzaById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Pizza pizza = foodService.getPizzaById(uuid);
            return ResponseEntity.ok(ApiResponse.success(pizza));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pizza não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("range/{min}/{max}")
    public ResponseEntity<ApiResponse<?>> getFoodByPriceRange(@PathVariable double min, @PathVariable double max) {
        try {
            BigDecimal minPrice = BigDecimal.valueOf(min);
            BigDecimal maxPrice = BigDecimal.valueOf(max);
            List<Food> foods = foodService.getFoodByPriceRange(minPrice, maxPrice);
            return ResponseEntity.ok(ApiResponse.successWithCount(foods, foods.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Faixa de preço inválida"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }
}
