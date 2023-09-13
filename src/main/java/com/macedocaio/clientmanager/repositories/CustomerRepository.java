package com.macedocaio.clientmanager.repositories;

import com.macedocaio.clientmanager.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByResourceId(UUID resourceId);

    void deleteByResourceId(UUID resourceId);
}
