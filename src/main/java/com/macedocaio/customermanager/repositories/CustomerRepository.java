package com.macedocaio.customermanager.repositories;

import com.macedocaio.customermanager.dto.customer.UpdateCustomerDto;
import com.macedocaio.customermanager.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByResourceId(UUID resourceId);

    Optional<CustomerEntity> findByUsername(String username);

    Optional<CustomerEntity> findByCpf(String cpf);

    void deleteByResourceId(UUID resourceId);

    @Modifying
    @Query(value = "update CustomerEntity c set c.firstname = :#{#customer.firstname}, c.lastname = :#{#customer.lastname}," +
            "c.birthday = :#{#customer.birthday} where c.resourceId = :resourceId")
    void updateByResourceId(@Param("resourceId") UUID resourceId, @Param("customer") UpdateCustomerDto customer);


}
