package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.dtos.CreateClientDTO;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getUserById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Client client = userService.getUserById(uuid);
            return ResponseEntity.ok(client);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Client> createUser(@RequestBody CreateClientDTO dto) {
        try {
            Client client = userService.createUser(dto);
            URI location = URI.create("/users/" + client.getId());
            return ResponseEntity.created(location).body(client);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
