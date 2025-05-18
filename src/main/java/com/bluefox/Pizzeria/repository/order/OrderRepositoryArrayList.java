package com.bluefox.Pizzeria.repository.order;

import com.bluefox.Pizzeria.interfaces.IOrderRepository;
import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.order.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderRepositoryArrayList implements IOrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order object) throws IllegalArgumentException, IllegalStateException {
        if (object == null || object.getId() == null) {
            throw new IllegalArgumentException("Pedido ou ID não pode ser nulo.");
        }

        boolean exists = orders.stream()
                .anyMatch(o -> o.getId().equals(object.getId()));
        if (exists) {
            throw new IllegalStateException("Pedido com ID '" + object.getId() + "' já existe.");
        }

        orders.add(object);
    }

    @Override
    public Order findById(UUID id) throws IllegalArgumentException, NoSuchElementException {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");

        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Pedido com ID '" + id + "' não encontrado."));
    }

    @Override
    public Order findByCustomerId(UUID customerId) throws IllegalArgumentException, NoSuchElementException {
        if (customerId == null) {
            throw new IllegalArgumentException("ID do cliente não pode ser nulo ou vazio.");
        }

        return orders.stream()
                .filter(o -> customerId.equals(o.getClientId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Pedido com ID do cliente '" + customerId + "' não encontrado."));
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) throws IllegalArgumentException, NoSuchElementException {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo.");
        }

        List<Order> result = orders.stream()
                .filter(o -> status.equals(o.getStatus()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NoSuchElementException("Nenhum pedido com status '" + status + "' encontrado.");
        }

        return result;
    }


    @Override
    public Order findByDeliveryAddress(String deliveryAddress) throws IllegalArgumentException, NoSuchElementException {
        if (deliveryAddress == null || deliveryAddress.isBlank()) {
            throw new IllegalArgumentException("Endereço de entrega não pode ser nulo ou vazio.");
        }

        return orders.stream()
                .filter(o -> deliveryAddress.equalsIgnoreCase(o.getDeliveryAddress()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Nenhum pedido com endereço '" + deliveryAddress + "' encontrado."));
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

        boolean removed = orders.removeIf(o -> o.getId().equals(id));

        if (!removed) {
            throw new NoSuchElementException("Pedido com ID '" + id + "' não encontrado para exclusão.");
        }
    }

    @Override
    public List<Order> findByPriceRange(double minPrice, double maxPrice) throws IllegalArgumentException, NoSuchElementException {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            throw new IllegalArgumentException("Faixa de preço inválida.");
        }

        BigDecimal min = BigDecimal.valueOf(minPrice);
        BigDecimal max = BigDecimal.valueOf(maxPrice);

        List<Order> result = orders.stream()
                .filter(o -> o.getTotalPrice() != null &&
                        o.getTotalPrice().compareTo(min) >= 0 &&
                        o.getTotalPrice().compareTo(max) <= 0)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NoSuchElementException("Nenhum pedido encontrado na faixa de preço especificada.");
        }

        return result;
    }

    @Override
    public List<Order> findByPaymentMethod(String paymentMethod) throws IllegalArgumentException, NoSuchElementException {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Método de pagamento não pode ser nulo ou vazio.");
        }

        List<Order> result = orders.stream()
                .filter(o -> paymentMethod.equalsIgnoreCase(o.getPaymentMethod()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NoSuchElementException("Nenhum pedido com método de pagamento '" + paymentMethod + "' encontrado.");
        }

        return result;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders); // Cópia defensiva
    }
}
