package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.dtos.CreateOrderDTO;
import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.services.OrderService;
import com.bluefox.Pizzeria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createOrder(@RequestBody CreateOrderDTO dto) {
        try {
            Order order = orderService.createOrder(dto);
            URI location = URI.create("/orders/" + order.getId());
            return ResponseEntity.created(location).body(ApiResponse.success(order));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Dados inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getOrderById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Order order = orderService.getOrderById(uuid);
            return ResponseEntity.ok(ApiResponse.success(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/client/phone/{phoneNumber}")
    public ResponseEntity<ApiResponse<?>> getOrderByClientPhone(@PathVariable String phoneNumber) {
        try {
            Client client = userService.getUserByPhoneNumber(phoneNumber);
            Order order = orderService.getOrderByCustomerId(client.getId());
            return ResponseEntity.ok(ApiResponse.success(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Nome inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            return ResponseEntity.ok(ApiResponse.successWithCount(orders, orders.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ApiResponse<?>> getOrdersByClientId(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Order orders = orderService.getOrderByCustomerId(uuid);
            return ResponseEntity.ok(ApiResponse.success(orders));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<?>> getOrdersByStatus(@PathVariable String status) {
        try {
            List<Order> orders = orderService.getOrdersByStatus(status);
            return ResponseEntity.ok(ApiResponse.successWithCount(orders, orders.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Status inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<ApiResponse<?>> getOrdersByDeliveryAddress(@PathVariable String address) {
        try {
            Order order = orderService.getOrderByDeliveryAddress(address);
            return ResponseEntity.ok(ApiResponse.success(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Endereço inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/payment/{paymentMethod}")
    public ResponseEntity<ApiResponse<?>> getOrdersByPaymentMethod(@PathVariable String paymentMethod) {
        try {
            List<Order> orders = orderService.getOrdersByPaymentMethod(paymentMethod);
            return ResponseEntity.ok(ApiResponse.successWithCount(orders, orders.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Método de pagamento inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/price/{minPrice}/{maxPrice}")
    public ResponseEntity<ApiResponse<?>> getOrdersByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        try {
            List<Order> orders = orderService.getOrdersByPriceRange(minPrice, maxPrice);
            return ResponseEntity.ok(ApiResponse.successWithCount(orders, orders.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Faixa de preço inválida"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateOrderStatus(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            orderService.updateOrderStatus(uuid);
            return ResponseEntity.ok(ApiResponse.success("Pedido atualizado com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteOrder(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            orderService.deleteOrderById(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }
}
