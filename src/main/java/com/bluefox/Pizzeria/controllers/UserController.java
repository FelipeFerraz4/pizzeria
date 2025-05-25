package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.controllers.docs.UserControllerDocs;
import com.bluefox.Pizzeria.dtos.CreateClientDTO;
import com.bluefox.Pizzeria.dtos.UpdateClientDTO;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.model.people.Person;
import com.bluefox.Pizzeria.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints para gerenciamento de usuários")
public class UserController implements UserControllerDocs {

    @Autowired
    private UserService userService;

    @PostMapping
    @Override
    public ResponseEntity<ApiResponses<?>> createUser(@RequestBody CreateClientDTO dto) {
        try {
            Client client = userService.createUser(dto);
            URI location = URI.create("/users/" + client.getId());
            return ResponseEntity.created(location).body(ApiResponses.success(client));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Dados inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> updateUser(@PathVariable String id, @RequestBody UpdateClientDTO dto) {
        try {
            UUID uuid = UUID.fromString(id);
            Client client = userService.updateClient(uuid, dto);
            return ResponseEntity.ok(ApiResponses.success(client));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> deleteUser(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            userService.deleteUserById(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ApiResponses<?>> getUserById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Client client = userService.getUserById(uuid);
            return ResponseEntity.ok(ApiResponses.success(client));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("ID inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<ApiResponses<?>> getAllUsers() {
        try {
            List<Person> people = userService.getAllUsers();
            return ResponseEntity.ok(ApiResponses.successWithCount(people, people.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/email/{email}")
    @Override
    public ResponseEntity<ApiResponses<?>> getUserByEmail(@PathVariable String email) {
        try {
            Client client = userService.getUserByEmail(email);
            return ResponseEntity.ok(ApiResponses.success(client));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Email inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/phone/{phoneNumber}")
    @Override
    public ResponseEntity<ApiResponses<?>> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        try {
            Client client = userService.getUserByPhoneNumber(phoneNumber);
            return ResponseEntity.ok(ApiResponses.success(client));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Telefone inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Usuário não encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/name/{name}")
    @Override
    public ResponseEntity<ApiResponses<?>> getUserByName(@PathVariable String name) {
        try {
            List<Person> people = userService.getUserByName(name);
            return ResponseEntity.ok(ApiResponses.successWithCount(people, people.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Nome inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Usuários não encontrados"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/client")
    @Override
    public ResponseEntity<ApiResponses<?>> getClient() {
        try {
            List<Client> clients = userService.getClients();
            return ResponseEntity.ok(ApiResponses.successWithCount(clients, clients.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Erro de validação"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Clientes não encontrados"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }
}
