package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.dtos.CreateOrderDTO;
import com.bluefox.Pizzeria.interfaces.IOrderRepository;
import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.order.OrderStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    private final IOrderRepository repository;

    public OrderService(@Qualifier("orderArrayList") IOrderRepository repository) {
        this.repository = repository;
    }

    public void createOrder(CreateOrderDTO order) throws IllegalArgumentException, IllegalStateException {
        Order newOrder = Order.builder()
                .clientId(UUID.fromString(order.clientId()))
                .deliveryAddress(order.deliveryAddress())
                .paymentMethod(order.paymentMethod())
                .items(order.items())
                .notes(order.notes())
                .build();
        repository.save(newOrder);
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

    public void updateOrder(Order order) throws IllegalArgumentException, NoSuchElementException {
        repository.updateById(order);
    }

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
        return repository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Order> getOrdersByPaymentMethod(String paymentMethod) throws IllegalArgumentException, NoSuchElementException {
        return repository.findByPaymentMethod(paymentMethod);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

}
