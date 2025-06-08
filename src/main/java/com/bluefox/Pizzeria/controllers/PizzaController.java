package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.controllers.docs.PizzaControllerDocs;
import com.bluefox.Pizzeria.dtos.CreatePizzaDTO;
import com.bluefox.Pizzeria.dtos.UpdatePizzaDTO;
import com.bluefox.Pizzeria.model.food.Pizza;
import com.bluefox.Pizzeria.services.PizzaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/pizzas")
@Tag(name = "Pizzas", description = "Endpoints para gerenciamento de pizzas")
@Slf4j
public class PizzaController implements PizzaControllerDocs {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    @Override
    public ResponseEntity<ApiResponses<?>> createPizza(@RequestBody @Valid CreatePizzaDTO dto) {
        try {
            Pizza pizza = pizzaService.createPizza(dto);
            URI location = URI.create("/pizzas/" + pizza.getId());
            return ResponseEntity.created(location).body(ApiResponses.success(pizza));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Dados inválidos"));
        } catch (Exception e) {
            log.error("Erro ao criar pizza", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<ApiResponses<?>> getAllPizzas() {
        try {
            List<Pizza> pizzas = pizzaService.getAllPizza();
            return ResponseEntity.ok(ApiResponses.successWithCount(pizzas, pizzas.size()));
        } catch (Exception e) {
            log.error("Erro ao buscar todas as pizzas", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> getPizzaById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Pizza pizza = pizzaService.getPizzaById(uuid);
            return ResponseEntity.ok(ApiResponses.success(pizza));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pizza não encontrada"));
        } catch (Exception e) {
            log.error("Erro ao buscar pizza por ID", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/name/{name}")
    @Override
    public ResponseEntity<ApiResponses<?>> getPizzaByName(@PathVariable String name) {
        try {
            List<Pizza> pizzas = pizzaService.getPizzaByName(name);
            return ResponseEntity.ok(ApiResponses.successWithCount(pizzas, pizzas.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Nome inválido"));
        } catch (Exception e) {
            log.error("Erro ao buscar pizza por nome", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> updatePizza(@PathVariable String id, @RequestBody @Valid UpdatePizzaDTO dto) {
        try {
            UUID uuid = UUID.fromString(id);
            Pizza pizza = pizzaService.updatePizza(uuid, dto);
            return ResponseEntity.ok(ApiResponses.success(pizza));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pizza não encontrada"));
        } catch (Exception e) {
            log.error("Erro ao atualizar pizza", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> deletePizza(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            pizzaService.deletePizzaById(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pizza não encontrada"));
        } catch (Exception e) {
            log.error("Erro ao deletar pizza", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/available")
    @Override
    public ResponseEntity<ApiResponses<?>> getAvailablePizzas() {
        try {
            List<Pizza> pizzas = pizzaService.getAvailablePizza();
            return ResponseEntity.ok(ApiResponses.successWithCount(pizzas, pizzas.size()));
        } catch (Exception e) {
            log.error("Erro ao buscar pizzas disponíveis", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/unavailable")
    @Override
    public ResponseEntity<ApiResponses<?>> getUnavailablePizzas() {
        try {
            List<Pizza> pizzas = pizzaService.getUnavailablePizza();
            return ResponseEntity.ok(ApiResponses.successWithCount(pizzas, pizzas.size()));
        } catch (Exception e) {
            log.error("Erro ao buscar pizzas indisponíveis", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/range/{min}/{max}")
    @Override
    public ResponseEntity<ApiResponses<?>> getPizzaByPriceRange(@PathVariable double min, @PathVariable double max) {
        try {
            BigDecimal minPrice = BigDecimal.valueOf(min);
            BigDecimal maxPrice = BigDecimal.valueOf(max);
            List<Pizza> pizzas = pizzaService.getPizzaByPriceRange(minPrice, maxPrice);
            return ResponseEntity.ok(ApiResponses.successWithCount(pizzas, pizzas.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Faixa de preço inválida"));
        } catch (Exception e) {
            log.error("Erro ao buscar pizzas por faixa de preço", e);
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }
}
