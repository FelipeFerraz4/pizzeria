package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.controllers.docs.OrderControllerDocs;
import com.bluefox.Pizzeria.dtos.CreateOrderDTO;
import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.services.OrderService;
import com.bluefox.Pizzeria.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Endpoints para gerenciamento de pedidos")
public class OrderController implements OrderControllerDocs {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    @Override
    public ResponseEntity<ApiResponses<?>> createOrder(@RequestBody CreateOrderDTO dto) {
        try {
            Order order = orderService.createOrder(dto);
            URI location = URI.create("/orders/" + order.getId());
            return ResponseEntity.created(location).body(ApiResponses.success(order));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Dados inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> getOrderById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Order order = orderService.getOrderById(uuid);
            return ResponseEntity.ok(ApiResponses.success(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/client/phone/{phoneNumber}")
    @Override
    public ResponseEntity<ApiResponses<?>> getOrderByClientPhone(@PathVariable String phoneNumber) {
        try {
            Client client = userService.getUserByPhoneNumber(phoneNumber);
            Order order = orderService.getOrderByCustomerId(client.getId());
            return ResponseEntity.ok(ApiResponses.success(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Nome inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<ApiResponses<?>> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            return ResponseEntity.ok(ApiResponses.successWithCount(orders, orders.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/client/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> getOrdersByClientId(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Order orders = orderService.getOrderByCustomerId(uuid);
            return ResponseEntity.ok(ApiResponses.success(orders));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/status/{status}")
    @Override
    public ResponseEntity<ApiResponses<?>> getOrdersByStatus(@PathVariable String status) {
        try {
            List<Order> orders = orderService.getOrdersByStatus(status);
            return ResponseEntity.ok(ApiResponses.successWithCount(orders, orders.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Status inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/address/{address}")
    @Override
    public ResponseEntity<ApiResponses<?>> getOrdersByDeliveryAddress(@PathVariable String address) {
        try {
            Order order = orderService.getOrderByDeliveryAddress(address);
            return ResponseEntity.ok(ApiResponses.success(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Endereço inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/payment/{paymentMethod}")
    @Override
    public ResponseEntity<ApiResponses<?>> getOrdersByPaymentMethod(@PathVariable String paymentMethod) {
        try {
            List<Order> orders = orderService.getOrdersByPaymentMethod(paymentMethod);
            return ResponseEntity.ok(ApiResponses.successWithCount(orders, orders.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Método de pagamento inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/price/{minPrice}/{maxPrice}")
    @Override
    public ResponseEntity<ApiResponses<?>> getOrdersByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        try {
            List<Order> orders = orderService.getOrdersByPriceRange(minPrice, maxPrice);
            return ResponseEntity.ok(ApiResponses.successWithCount(orders, orders.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Faixa de preço inválida"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> updateOrderStatus(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            orderService.updateOrderStatus(uuid);
            return ResponseEntity.ok(ApiResponses.success("Pedido atualizado com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> deleteOrder(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            orderService.deleteOrderById(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Pedido não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }
}
