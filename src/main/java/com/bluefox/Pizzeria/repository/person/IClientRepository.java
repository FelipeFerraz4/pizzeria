package com.bluefox.Pizzeria.repository.person;

import com.bluefox.Pizzeria.model.people.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("clientRepositoryJPA")
public interface IClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByPhoneNumber(String phoneNumber);

    List<Client> findByNameIgnoreCase(String name);
}
