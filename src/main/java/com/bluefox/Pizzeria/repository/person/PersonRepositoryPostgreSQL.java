package com.bluefox.Pizzeria.repository.person;

import com.bluefox.Pizzeria.interfaces.IPersonRepository;
import com.bluefox.Pizzeria.model.people.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Person entities in a PostgreSQL database.
 * This interface extends JpaRepository to provide basic CRUD operations.
 */

@Repository("personRepositoryPostgreSQL")
public interface PersonRepositoryPostgreSQL extends JpaRepository<Person, UUID>, IPersonRepository {
    // This interface extends JpaRepository, which provides basic CRUD operations.
    // Additional custom methods can be defined here if needed.
}
