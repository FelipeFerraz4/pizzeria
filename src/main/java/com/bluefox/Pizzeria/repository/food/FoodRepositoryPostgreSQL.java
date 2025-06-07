package com.bluefox.Pizzeria.repository.food;

import com.bluefox.Pizzeria.interfaces.IFoodRepository;
import com.bluefox.Pizzeria.model.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/** * Repository interface for managing Food entities in a PostgreSQL database.
 * This interface extends JpaRepository to provide basic CRUD operations.
 */

@Repository("foodRepositoryPostgreSQL")
public interface FoodRepositoryPostgreSQL extends JpaRepository<Food, UUID>, IFoodRepository {
    // This interface extends JpaRepository, which provides basic CRUD operations.
    // Additional custom methods can be defined here if needed.
}
