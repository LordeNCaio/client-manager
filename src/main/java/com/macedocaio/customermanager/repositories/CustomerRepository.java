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

    /**
     * Procura um objeto do tipo {@link CustomerEntity} pelo 'resourceId'
     *
     * @param resourceId Identificador que será utilizado na busca
     * @return {@link Optional<CustomerEntity>}
     */
    Optional<CustomerEntity> findByResourceId(UUID resourceId);

    /**
     * Procura um objeto do tipo {@link CustomerEntity} pelo 'username'
     *
     * @param username Identificador que será utilizado na busca
     * @return {@link Optional<CustomerEntity>}
     */
    Optional<CustomerEntity> findByUsername(String username);

    /**
     * Procura um objeto do tipo {@link CustomerEntity} pelo 'cpf'
     *
     * @param cpf Identificador que será utilizado na busca
     * @return {@link Optional<CustomerEntity>}
     */
    Optional<CustomerEntity> findByCpf(String cpf);

    /**
     * Atualiza um objeto do tipo {@link CustomerEntity} pelo 'resourceId'
     *
     * @param resourceId        Identificador que será utilizado na busca
     * @param updateCustomerDto Objeto contendo as atualizações
     */
    @Modifying
    @Query(value = "update CustomerEntity c set c.firstname = :#{#customer.firstname}, " +
            "c.lastname = :#{#customer.lastname}," + "c.birthday = :#{#customer.birthday} " +
            "where c.resourceId = :resourceId")
    void updateByResourceId(@Param("resourceId") UUID resourceId,
                            @Param("customer") UpdateCustomerDto updateCustomerDto);

    /**
     * Deleta um objeto do tipo {@link CustomerEntity} pelo 'resourceId'
     *
     * @param resourceId Identificador que será utilizado na busca
     */
    void deleteByResourceId(UUID resourceId);
}
