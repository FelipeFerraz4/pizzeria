package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.dtos.CreateClientDTO;
import com.bluefox.Pizzeria.dtos.UpdateClientDTO;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.model.people.Person;
import com.bluefox.Pizzeria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Client client = userService.getUserById(uuid);
            return ResponseEntity.ok(ApiResponse.success(client));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody CreateClientDTO dto) {
        try {
            Client client = userService.createUser(dto);
            URI location = URI.create("/users/" + client.getId());
            return ResponseEntity.created(location).body(ApiResponse.success(client));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Dados inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable String id, @RequestBody UpdateClientDTO dto) {
        try {
            UUID uuid = UUID.fromString(id);
            Client client = userService.updateClient(uuid, dto);
            return ResponseEntity.ok(ApiResponse.success(client));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            userService.deleteUserById(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllUsers() {
        try {
            List<Person> people = userService.getAllUsers();
            return ResponseEntity.ok(ApiResponse.successWithCount(people, people.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<?>> getUserByEmail(@PathVariable String email) {
        try {
            Client client = userService.getUserByEmail(email);
            return ResponseEntity.ok(ApiResponse.success(client));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Email inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<ApiResponse<?>> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        try {
            Client client = userService.getUserByPhoneNumber(phoneNumber);
            return ResponseEntity.ok(ApiResponse.success(client));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Telefone inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<?>> getUserByName(@PathVariable String name) {
        try {
            List<Person> people = userService.getUserByName(name);
            return ResponseEntity.ok(ApiResponse.successWithCount(people, people.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Nome inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Usuários não encontrados"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }

    @GetMapping("/client")
    public ResponseEntity<ApiResponse<?>> getClient() {
        try {
            List<Client> clients = userService.getClients();
            return ResponseEntity.ok(ApiResponse.successWithCount(clients, clients.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Erro de validação"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponse.error("Clientes não encontrados"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error("Erro interno"));
        }
    }
}
