package com.bluefox.Pizzeria.controllers;

import com.bluefox.Pizzeria.controllers.docs.ClientControllerDocs;
import com.bluefox.Pizzeria.dtos.CreateClientDTO;
import com.bluefox.Pizzeria.dtos.UpdateClientDTO;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Endpoints para gerenciamento de clientes")
public class ClientController implements ClientControllerDocs {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Override
    public ResponseEntity<ApiResponses<?>> createClient(@RequestBody CreateClientDTO dto) {
        try {
            Client client = clientService.createClient(dto);
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
    public ResponseEntity<ApiResponses<?>> updateClient(@PathVariable String id, @RequestBody UpdateClientDTO dto) {
        try {
            UUID uuid = UUID.fromString(id);
            Client client = clientService.updateClient(uuid, dto);
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
    public ResponseEntity<ApiResponses<?>> deleteClient(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            clientService.deleteClientById(uuid);
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
    public ResponseEntity<ApiResponses<?>> getClientById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Client client = clientService.getClientById(uuid);
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
    public ResponseEntity<ApiResponses<?>> getAllClients() {
        try {
            List<Client> clients = clientService.getAllClients();
            return ResponseEntity.ok(ApiResponses.successWithCount(clients, clients.size()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }

    @GetMapping("/email/{email}")
    @Override
    public ResponseEntity<ApiResponses<?>> getClientByEmail(@PathVariable String email) {
        try {
            Client client = clientService.getClientByEmail(email);
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
    public ResponseEntity<ApiResponses<?>> getClientByPhoneNumber(@PathVariable String phoneNumber) {
        try {
            Client client = clientService.getClientsByPhoneNumber(phoneNumber);
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
    public ResponseEntity<ApiResponses<?>> getClientByName(@PathVariable String name) {
        try {
            List<Client> clients = clientService.getClientsByName(name);
            return ResponseEntity.ok(ApiResponses.successWithCount(clients, clients.size()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponses.error("Nome inválido"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(ApiResponses.error("Usuários não encontrados"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponses.error("Erro interno"));
        }
    }
}
