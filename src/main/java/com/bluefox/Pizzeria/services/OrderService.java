package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreateOrderDTO;
import com.bluefox.Pizzeria.interfaces.IOrderRepository;
import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.order.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    private final IOrderRepository repository;
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);


    public OrderService(@Qualifier("orderArrayList") IOrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(CreateOrderDTO order) throws IllegalArgumentException, IllegalStateException {
        BigDecimal totalPrice = order.items().stream()
                .map(item -> item.getTotalPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order newOrder = Order.builder()
                .clientId(UUID.fromString(order.clientId()))
                .deliveryAddress(order.deliveryAddress())
                .paymentMethod(order.paymentMethod())
                .items(order.items())
                .notes(order.notes())
                .totalPrice(totalPrice)
                .build();
        repository.save(newOrder);
        return newOrder;
    }

    public Order getOrderById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        return repository.findById(id);
    }

    public Order getOrderByCustomerId(UUID customerId) throws IllegalArgumentException, NoSuchElementException {
        return repository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByStatus(String status) throws IllegalArgumentException, NoSuchElementException {
        OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
        return repository.findByStatus(newStatus);
    }

    public Order getOrderByDeliveryAddress(String deliveryAddress) throws IllegalArgumentException, NoSuchElementException {
        return repository.findByDeliveryAddress(deliveryAddress);
    }

//    public void updateOrder(Order order) throws IllegalArgumentException, NoSuchElementException {
//        repository.updateById(order);
//    }

    public void updateOrderStatus(UUID id) throws IllegalArgumentException, NoSuchElementException {
        Order order = repository.findById(id);
        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.IN_PREPARATION);
        } else if (order.getStatus() == OrderStatus.IN_PREPARATION) {
            order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        } else if (order.getStatus() == OrderStatus.OUT_FOR_DELIVERY) {
            order.setStatus(OrderStatus.DELIVERED);
        }
        repository.updateById(order);
    }

    public void deleteOrderById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        repository.deleteByID(id);
    }

    public List<Order> getOrdersByPriceRange(double minPrice, double maxPrice) throws IllegalArgumentException, NoSuchElementException {
        log.info("Buscando pedidos com faixa de preço entre {} e {}", minPrice, maxPrice);

        try {
            List<Order> orders = repository.findByPriceRange(minPrice, maxPrice);
            log.info("Foram encontrados {} pedidos na faixa de preço.", orders.size());
            return orders;
        } catch (IllegalArgumentException e) {
            log.warn("Faixa de preço inválida: min={} max={}", minPrice, maxPrice);
            throw e;
        } catch (NoSuchElementException e) {
            log.info("Nenhum pedido encontrado na faixa de preço entre {} e {}", minPrice, maxPrice);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao buscar pedidos por faixa de preço", e);
            throw e;
        }
    }

    public List<Order> getOrdersByPaymentMethod(String paymentMethod) throws IllegalArgumentException, NoSuchElementException {
        return repository.findByPaymentMethod(paymentMethod);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

}
