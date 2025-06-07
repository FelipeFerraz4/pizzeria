package com.bluefox.Pizzeria.repository.order;

import com.bluefox.Pizzeria.interfaces.IOrderRepository;
import com.bluefox.Pizzeria.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Order entities in a PostgreSQL database.
 * This interface extends JpaRepository to provide basic CRUD operations.
 */

@Repository("orderRepositoryPostgreSQL")
public interface OrderRepositoryPostgreSQL extends JpaRepository<Order, UUID>, IOrderRepository {

}
