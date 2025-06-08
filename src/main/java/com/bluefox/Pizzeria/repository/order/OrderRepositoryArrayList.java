package com.bluefox.Pizzeria.repository.order;

import com.bluefox.Pizzeria.interfaces.IOrderRepository;
import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.order.OrderStatus;
import com.bluefox.Pizzeria.model.people.Client;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository("orderArrayList")
public class OrderRepositoryArrayList implements IOrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Optional<Order> save(Order object) throws IllegalArgumentException, IllegalStateException {
        if (object == null || object.getId() == null) {
            throw new IllegalArgumentException("Pedido ou ID não pode ser nulo.");
        }

        boolean exists = orders.stream()
                .anyMatch(o -> o.getId().equals(object.getId()));
        if (exists) {
            throw new IllegalStateException("Pedido com ID '" + object.getId() + "' já existe.");
        }

        orders.add(object);
        return Optional.of(object);
    }

    @Override
    public Optional<Order> findById(UUID id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Order> findByClient(Client client) throws IllegalArgumentException {
        if (client == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }

        UUID customerId = client.getId();

        return orders.stream()
                .filter(o -> o.getClient() != null && customerId.equals(o.getClient().getId()))
                .max(Comparator.comparing(Order::getCreatedAt));
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) throws IllegalArgumentException {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo.");
        }

        return orders.stream()
                .filter(o -> status.equals(o.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findByDeliveryAddressIgnoreCase(String deliveryAddress) throws IllegalArgumentException {
        if (deliveryAddress == null || deliveryAddress.isBlank()) {
            throw new IllegalArgumentException("Endereço de entrega não pode ser nulo ou vazio.");
        }

        return orders.stream()
                .filter(o -> deliveryAddress.equalsIgnoreCase(o.getDeliveryAddress()))
                .max(Comparator.comparing(Order::getCreatedAt));
    }

    @Override
    public void updateById(Order object) throws IllegalArgumentException, NoSuchElementException {
        if (object == null || object.getId() == null) {
            throw new IllegalArgumentException("Pedido ou ID não pode ser nulo.");
        }

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(object.getId())) {
                orders.set(i, object);
                return;
            }
        }

        throw new NoSuchElementException("Pedido com ID '" + object.getId() + "' não encontrado para atualização.");
    }

    @Override
    public void deleteByID(UUID id) throws IllegalArgumentException, NoSuchElementException {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");

        Order order = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido com ID '" + id + "' não encontrado."));

        order.setStatus(OrderStatus.CANCELED);
        updateById(order);
    }

    @Override
    public List<Order> findByTotalPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) throws IllegalArgumentException {
        if (minPrice == null || maxPrice == null) {
            throw new IllegalArgumentException("Valores de preço não podem ser nulos.");
        }
        if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0 || minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("Faixa de preço inválida.");
        }

        return orders.stream()
                .filter(o -> o.getTotalPrice() != null &&
                        o.getTotalPrice().compareTo(minPrice) >= 0 &&
                        o.getTotalPrice().compareTo(maxPrice) <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByPaymentMethodIgnoreCase(String paymentMethod) throws IllegalArgumentException {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Método de pagamento não pode ser nulo ou vazio.");
        }

        return orders.stream()
                .filter(o -> paymentMethod.equalsIgnoreCase(o.getPaymentMethod()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders); // Cópia defensiva
    }
}
