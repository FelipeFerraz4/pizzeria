package com.bluefox.Pizzeria.interfaces;

import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.order.OrderStatus;
import com.bluefox.Pizzeria.model.people.Client;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface IOrderRepository extends IRepository<Order> {
    Optional<Order> findByClient(Client client) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByStatus(OrderStatus status) throws IllegalArgumentException, NoSuchElementException;
    Optional<Order> findByDeliveryAddressIgnoreCase(String deliveryAddress) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByTotalPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) throws IllegalArgumentException, NoSuchElementException;
    List<Order> findByPaymentMethodIgnoreCase(String paymentMethod) throws IllegalArgumentException, NoSuchElementException;
}
