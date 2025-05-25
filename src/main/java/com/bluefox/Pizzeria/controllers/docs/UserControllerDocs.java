package com.bluefox.Pizzeria.controllers.docs;

import com.bluefox.Pizzeria.controllers.ApiResponses;
import com.bluefox.Pizzeria.dtos.CreateClientDTO;
import com.bluefox.Pizzeria.dtos.UpdateClientDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserControllerDocs {
    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna um usuário específico com base no ID fornecido. " +
                    "Se o ID não for válido ou o usuário não for encontrado, retorna um erro.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> getUserById(@PathVariable String id);

    @Operation(
            summary = "Criar um novo usuário",
            description = "Cria um novo usuário com os dados fornecidos. " +
                    "Se os dados forem inválidos, retorna um erro.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> createUser(@RequestBody CreateClientDTO dto);

    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário específico com base no ID fornecido. " +
                    "Se o ID não for válido ou o usuário não for encontrado, retorna um erro.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> updateUser(@PathVariable String id, @RequestBody UpdateClientDTO dto);

    @Operation(
            summary = "Deletar usuário",
            description = "Deleta um usuário específico com base no ID fornecido. " +
                    "Se o ID não for válido ou o usuário não for encontrado, retorna um erro.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> deleteUser(@PathVariable String id);

    @Operation(
            summary = "Buscar todos os usuários",
            description = "Retorna uma lista de todos os usuários. " +
                    "Se não houver usuários, retorna uma lista vazia.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> getAllUsers();

    @Operation(
            summary = "Buscar usuário por email",
            description = "Retorna um usuário específico com base no email fornecido. " +
                    "Se o email não for válido ou o usuário não for encontrado, retorna um erro.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> getUserByEmail(@PathVariable String email);

    @Operation(
            summary = "Buscar usuário por número de telefone",
            description = "Retorna um usuário específico com base no número de telefone fornecido. " +
                    "Se o número de telefone não for válido ou o usuário não for encontrado, retorna um erro.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> getUserByPhoneNumber(@PathVariable String phoneNumber);

    @Operation(
            summary = "Buscar usuário por nome",
            description = "Retorna um usuário específico com base no nome fornecido. " +
                    "Se o nome não for válido ou o usuário não for encontrado, retorna um erro.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> getUserByName(@PathVariable String name);

    @Operation(
            summary = "Buscar cliente",
            description = "Retorna um cliente específico. " +
                    "Se o cliente não for encontrado, retorna um erro.",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ApiResponses.class))
                    )
            }
    )
    ResponseEntity<ApiResponses<?>> getClient();
}
