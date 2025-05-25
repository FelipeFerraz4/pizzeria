package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.controllers.docs.FoodControllerDocs;
import com.bluefox.Pizzeria.dtos.CreatePizzaDTO;
import com.bluefox.Pizzeria.dtos.UpdatePizzaDTO;
import com.bluefox.Pizzeria.model.food.Food;
import com.bluefox.Pizzeria.model.food.Pizza;
import com.bluefox.Pizzeria.services.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Foods", description = "Endpoints para gerenciamento de comidas")
public class FoodController implements FoodControllerDocs {

    @Autowired
    private FoodService foodService;

    @PostMapping
    @Override
    public ResponseEntity<ApiResponses<?>> createFood(@RequestBody CreatePizzaDTO dto) {
        try {
            Pizza pizza = foodService.createFood(dto);
            URI location = URI.create("/food/" + pizza.getId());
            return ResponseEntity.created(location).body(ApiResponses.success(pizza));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Dados inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<ApiResponses<?>> getAllFoods() {
        try {
            List<Food> foods = foodService.getAllFood();
            return ResponseEntity.ok(ApiResponses.successWithCount(foods, foods.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> getFoodById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Food food = foodService.getFoodById(uuid);
            return ResponseEntity.ok(ApiResponses.success(food));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/name/{name}")
    @Override
    public ResponseEntity<ApiResponses<?>> getFoodByName(@PathVariable String name) {
        try {
            List<Food> foods = foodService.getFoodByName(name);
            return ResponseEntity.ok(ApiResponses.successWithCount(foods, foods.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Nome inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> updateFood(@PathVariable String id, @RequestBody UpdatePizzaDTO dto) {
        try {
            UUID uuid = UUID.fromString(id);
            Food food = foodService.updatePizza(uuid, dto);
            return ResponseEntity.ok(ApiResponses.success(food));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> deleteFood(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            foodService.deleteFoodById(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/available")
    @Override
    public ResponseEntity<ApiResponses<?>> getAvailableFoods() {
        try {
            List<Food> foods = foodService.getAvailableFood();
            return ResponseEntity.ok(ApiResponses.successWithCount(foods, foods.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/unavailable")
    @Override
    public ResponseEntity<ApiResponses<?>> getUnavailableFoods() {
        try {
            List<Food> foods = foodService.getUnavailableFood();
            return ResponseEntity.ok(ApiResponses.successWithCount(foods, foods.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/pizza")
    @Override
    public ResponseEntity<ApiResponses<?>> getAllPizzas() {
        try {
            List<Pizza> pizzas = foodService.getPizzas();
            return ResponseEntity.ok(ApiResponses.successWithCount(pizzas, pizzas.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/pizza/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> getPizzaById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Pizza pizza = foodService.getPizzaById(uuid);
            return ResponseEntity.ok(ApiResponses.success(pizza));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pizza não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("range/{min}/{max}")
    @Override
    public ResponseEntity<ApiResponses<?>> getFoodByPriceRange(@PathVariable double min, @PathVariable double max) {
        try {
            BigDecimal minPrice = BigDecimal.valueOf(min);
            BigDecimal maxPrice = BigDecimal.valueOf(max);
            List<Food> foods = foodService.getFoodByPriceRange(minPrice, maxPrice);
            return ResponseEntity.ok(ApiResponses.successWithCount(foods, foods.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Faixa de preço inválida"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Comida não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }
}
