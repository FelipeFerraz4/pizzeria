package com.bluefox.Pizzeria.controllers.docs;

import com.bluefox.Pizzeria.controllers.ApiResponses;
import com.bluefox.Pizzeria.dtos.CreateOrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface OrderControllerDocs {
    @Operation(
            summary = "Criar um novo pedido",
            description = "Cria um novo pedido com os dados fornecidos. Retorna o pedido criado.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> createOrder(@RequestBody CreateOrderDTO dto);

    @Operation(
            summary = "Buscar pedido por ID",
            description = "Busca um pedido pelo ID fornecido. Retorna o pedido encontrado.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> getOrderById(@PathVariable String id);

    @Operation(
            summary = "Buscar pedido por telefone do cliente",
            description = "Busca um pedido pelo telefone do cliente fornecido. Retorna o pedido encontrado.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> getOrderByClientPhone(@PathVariable String phoneNumber);

    @Operation(
            summary = "Buscar todos os pedidos",
            description = "Busca todos os pedidos. Retorna a lista de pedidos encontrados.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> getAllOrders();

    @Operation(
            summary = "Buscar pedidos por ID do cliente",
            description = "Busca pedidos pelo ID do cliente fornecido. Retorna a lista de pedidos encontrados.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> getOrdersByClientId(@PathVariable String id);

    @Operation(
            summary = "Buscar pedidos por status",
            description = "Busca pedidos pelo status fornecido. Retorna a lista de pedidos encontrados.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> getOrdersByStatus(@PathVariable String status);

    @Operation(
            summary = "Buscar pedidos por endereço de entrega",
            description = "Busca pedidos pelo endereço de entrega fornecido. Retorna a lista de pedidos encontrados.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> getOrdersByDeliveryAddress(@PathVariable String address);

    @Operation(
            summary = "Buscar pedidos por método de pagamento",
            description = "Busca pedidos pelo método de pagamento fornecido. Retorna a lista de pedidos encontrados.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> getOrdersByPaymentMethod(@PathVariable String paymentMethod);

    @Operation(
            summary = "Buscar pedidos por faixa de preço",
            description = "Busca pedidos pela faixa de preço fornecida. Retorna a lista de pedidos encontrados.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> getOrdersByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice);

    @Operation(
            summary = "Atualizar status do pedido",
            description = "Atualiza o status do pedido com o ID fornecido. Retorna o pedido atualizado.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> updateOrderStatus(@PathVariable String id);

    @Operation(
            summary = "Deletar pedido",
            description = "Deleta o pedido com o ID fornecido. Retorna uma resposta de sucesso.",
            tags = {"Orders"},
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
    ResponseEntity<ApiResponses<?>> deleteOrder(@PathVariable String id);
}
