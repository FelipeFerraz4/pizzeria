package com.bluefox.Pizzeria.interfaces;

import com.bluefox.Pizzeria.model.order.Order;

import java.util.List;
import java.util.NoSuchElementException;

public interface IOrderRepository extends IRepository<Order> {
    Order findByCustomerId(String customerId) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByStatus(String status) throws IllegalArgumentException, NoSuchElementException;
    Order findByDeliveryAddress(String deliveryAddress) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByPriceRange(double minPrice, double maxPrice) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByPaymentMethod(String paymentMethod) throws IllegalArgumentException, NoSuchElementException;
}
