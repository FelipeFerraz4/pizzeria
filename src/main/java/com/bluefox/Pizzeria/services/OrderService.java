package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreateOrderDTO;
import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.order.OrderStatus;
import com.bluefox.Pizzeria.model.people.Client;
import com.bluefox.Pizzeria.repository.order.IOrderRepository;
import com.bluefox.Pizzeria.repository.person.IClientRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class OrderService {

    private final IOrderRepository repository;
    private final IClientRepository clientRepository;

    public OrderService(@Qualifier("orderRepositoryJPA") IOrderRepository repository,
                        @Qualifier("clientRepositoryJPA") IClientRepository clientRepository) {
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    public Order createOrder(CreateOrderDTO orderDTO) {
        UUID clientId = UUID.fromString(orderDTO.clientId());
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Cliente com ID '" + clientId + "' não encontrado."));

        BigDecimal totalPrice = orderDTO.items().stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order newOrder = Order.builder()
                .client(client)
                .deliveryAddress(orderDTO.deliveryAddress())
                .paymentMethod(orderDTO.paymentMethod())
                .items(orderDTO.items())
                .notes(orderDTO.notes())
                .totalPrice(totalPrice)
                .status(OrderStatus.PENDING)
                .build();

        return repository.saveAndFlush(newOrder);
    }

    public Order getOrderById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido com ID '" + id + "' não encontrado."));
    }

    public List<Order> getOrdersByClientId(UUID clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Cliente com ID '" + clientId + "' não encontrado."));
        return repository.findByClient(client);
    }

    public List<Order> getOrdersByStatus(String status) {
        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            return repository.findByStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status de pedido inválido: " + status);
        }
    }

    public List<Order> getOrdersByDeliveryAddress(String deliveryAddress) {
        return repository.findByDeliveryAddressIgnoreCase(deliveryAddress);
    }

    public void updateOrderStatus(UUID id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido com ID '" + id + "' não encontrado."));

        switch (order.getStatus()) {
            case PENDING -> order.setStatus(OrderStatus.IN_PREPARATION);
            case IN_PREPARATION -> order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
            case OUT_FOR_DELIVERY -> order.setStatus(OrderStatus.DELIVERED);
            case DELIVERED -> log.info("Pedido {} já foi entregue. Nenhuma atualização feita.", id);
        }

        repository.saveAndFlush(order);
    }

    public void deleteOrderById(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Pedido com ID '" + id + "' não encontrado.");
        }
        repository.deleteById(id);
    }

    public List<Order> getOrdersByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            throw new IllegalArgumentException("Faixa de preço inválida: min=" + minPrice + ", max=" + maxPrice);
        }

        BigDecimal min = BigDecimal.valueOf(minPrice);
        BigDecimal max = BigDecimal.valueOf(maxPrice);

        return repository.findByTotalPriceBetween(min, max);
    }

    public List<Order> getOrdersByPaymentMethod(String paymentMethod) {
        return repository.findByPaymentMethodIgnoreCase(paymentMethod);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}
