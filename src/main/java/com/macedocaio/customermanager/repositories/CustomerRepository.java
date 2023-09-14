package com.macedocaio.customermanager.repositories;

import com.macedocaio.customermanager.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByResourceId(UUID resourceId);

    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByCpf(String cpf);

    void deleteByResourceId(UUID resourceId);

    @Modifying
    @Query(value = "update Customer c set c.firstname = :#{#customer.firstname}, c.lastname = :#{#customer.lastname}," +
            "c.birthday = :#{#customer.birthday} where c.resourceId = :resourceId")
    void updateByResourceId(@Param("resourceId") UUID resourceId, @Param("customer") Customer customer);


}
