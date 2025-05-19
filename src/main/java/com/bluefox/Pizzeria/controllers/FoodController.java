package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.dtos.CreatePizzaDTO;
import com.bluefox.Pizzeria.model.food.Pizza;
import com.bluefox.Pizzeria.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
