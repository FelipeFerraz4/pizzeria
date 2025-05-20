package com.bluefox.Pizzeria.services;

import com.bluefox.Pizzeria.interfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final IOrderRepository repository;

    public OrderService(@Qualifier("orderArrayList") IOrderRepository repository) {
        this.repository = repository;
    }

    public void createOrder(Order order) {
        repository.save(order);
    }
}
