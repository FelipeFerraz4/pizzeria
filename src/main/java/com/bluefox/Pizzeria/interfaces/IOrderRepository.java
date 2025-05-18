package com.bluefox.Pizzeria.interfaces;

import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.order.OrderStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface IOrderRepository extends IRepository<Order> {
    Order findByCustomerId(UUID customerId) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByStatus(OrderStatus status) throws IllegalArgumentException, NoSuchElementException;
    Order findByDeliveryAddress(String deliveryAddress) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByPriceRange(double minPrice, double maxPrice) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByPaymentMethod(String paymentMethod) throws IllegalArgumentException, NoSuchElementException;
}
