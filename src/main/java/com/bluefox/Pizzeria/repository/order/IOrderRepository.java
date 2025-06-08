package com.bluefox.Pizzeria.repository.order;

import com.bluefox.Pizzeria.model.order.Order;
import com.bluefox.Pizzeria.model.order.OrderStatus;
import com.bluefox.Pizzeria.model.people.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository("orderRepositoryJPA")
public interface IOrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByClient(Client client);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByDeliveryAddressIgnoreCase(String deliveryAddress);

    List<Order> findByTotalPriceBetween(BigDecimal min, BigDecimal max);

    List<Order> findByPaymentMethodIgnoreCase(String paymentMethod);
}
