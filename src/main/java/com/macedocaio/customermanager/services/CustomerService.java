package com.macedocaio.customermanager.services;


import com.macedocaio.customermanager.dto.customer.UpdateCustomerDto;
import com.macedocaio.customermanager.entities.CustomerEntity;
import com.macedocaio.customermanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.customer.CustomerNotFoundException;
import com.macedocaio.customermanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.customermanager.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author caiom
 * Classe responsavel pela manipulação da informaçao entre as classes
 * {@link com.macedocaio.customermanager.controllers.CustomerController} e {@link CustomerRepository}
 */
@Service
public class CustomerService {

    /**
     * Objeto utilizado para a comunicação com o banco de dados
     */
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Cria um novo registo do tipo {@link CustomerEntity} no banco de dados
     *
     * @param customer que será inserido no banco de dados
     * @return entidade persistida no banco de dados
     */
    public CustomerEntity createSingle(CustomerEntity customer) {
        isValidUsername(customer);
        isValidCpf(customer);
        return repository.save(customer);
    }

    /**
     * Busca um registo do tipo {@link CustomerEntity} no banco de dados utilizando o 'resourceId'
     *
     * @param resourceId que será utilizado para encontrar o {@link CustomerEntity}
     * @return um objeto do tipo {@link CustomerEntity}
     * @throws CustomerNotFoundException caso não seja encontrado um {@link CustomerEntity}
     */
    public CustomerEntity findByResourceId(UUID resourceId) throws CustomerNotFoundException {
        Optional<CustomerEntity> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(resourceId);
        }

        return optionalCustomer.get();
    }

    /**
     * Retorna todos os objetos do tipo {@link CustomerEntity}
     * @return {@link List<CustomerEntity>}
     */
    public List<CustomerEntity> findAll() {
        return repository.findAll();
    }

    /**
     * Atualiza um registo do tipo {@link CustomerEntity} no banco de dados utilizando o 'resourceId'
     *
     * @param resourceId que será utilizado para encontrar o {@link CustomerEntity}
     * @param customer   contendo as alterações para serem salvas
     * @throws CustomerNotFoundException caso não seja encontrado um {@link CustomerEntity}
     */
    public void updateByResourceId(UUID resourceId, UpdateCustomerDto customer) throws CustomerNotFoundException {
        Optional<CustomerEntity> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(resourceId);
        }

        repository.updateByResourceId(resourceId, customer);
    }

    /**
     * Deleta um registo do tipo {@link CustomerEntity} no banco de dados utilizando o 'resourceId'
     *
     * @param resourceId que será utilizado para encontrar o {@link CustomerEntity}
     * @throws CustomerNotFoundException caso não seja encontrado um {@link CustomerEntity}
     */
    public void deleteByResourceId(UUID resourceId) throws CustomerNotFoundException {
        Optional<CustomerEntity> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(resourceId);
        }

        repository.deleteByResourceId(resourceId);
    }

    /**
     * Verifica se {@link CustomerEntity#getUsername()} já existe no banco de dados
     *
     * @param customer que será buscado pelo {@link CustomerEntity#getUsername()}
     * @throws UsernameAlreadyInUseException caso 'username' já esteja em uso.
     */
    private void isValidUsername(CustomerEntity customer) throws UsernameAlreadyInUseException {
        Optional<CustomerEntity> optionalCustomer = repository.findByUsername(customer.getUsername());
        optionalCustomer.ifPresent(value -> {
            throw new UsernameAlreadyInUseException(value);
        });
    }

    /**
     * Verifica se {@link CustomerEntity#getCpf()} já existe no banco de dados
     *
     * @param customer que será buscado pelo {@link CustomerEntity#getCpf()}
     * @throws CpfAlreadyInUseException caso 'cpf' já esteja em uso.
     */
    private void isValidCpf(CustomerEntity customer) throws CpfAlreadyInUseException {
        Optional<CustomerEntity> optionalCustomer = repository.findByCpf(customer.getCpf());
        optionalCustomer.ifPresent(value -> {
            throw new CpfAlreadyInUseException(value);
        });
    }
}
