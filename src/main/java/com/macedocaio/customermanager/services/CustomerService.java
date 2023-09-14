package com.macedocaio.customermanager.services;


import com.macedocaio.customermanager.entities.Customer;
import com.macedocaio.customermanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.customer.CustomerNotFoundException;
import com.macedocaio.customermanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.customermanager.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

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
     * Cria um novo registo do tipo {@link Customer} no banco de dados
     *
     * @param customer que será inserido no banco de dados
     * @return entidade persistida no banco de dados
     */
    public Customer createSingle(Customer customer) {
        isValidUsername(customer);
        isValidCpf(customer);
        return repository.save(customer);
    }

    /**
     * Busca um registo do tipo {@link Customer} no banco de dados utilizando o 'resourceId'
     *
     * @param resourceId que será utilizado para encontrar o {@link Customer}
     * @return um objeto do tipo {@link Customer}
     * @throws CustomerNotFoundException caso não seja encontrado um {@link Customer}
     */
    public Customer findByResourceId(UUID resourceId) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(resourceId);
        }

        return optionalCustomer.get();
    }

    /**
     * Atualiza um registo do tipo {@link Customer} no banco de dados utilizando o 'resourceId'
     *
     * @param resourceId que será utilizado para encontrar o {@link Customer}
     * @param customer   contendo as alterações para serem salvas
     * @throws CustomerNotFoundException caso não seja encontrado um {@link Customer}
     */
    public void updateByResourceId(UUID resourceId, Customer customer) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(resourceId);
        }

        repository.updateByResourceId(resourceId, customer);
    }

    /**
     * Deleta um registo do tipo {@link Customer} no banco de dados utilizando o 'resourceId'
     *
     * @param resourceId que será utilizado para encontrar o {@link Customer}
     * @throws CustomerNotFoundException caso não seja encontrado um {@link Customer}
     */
    public void deleteByResourceId(UUID resourceId) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(resourceId);
        }

        repository.deleteByResourceId(resourceId);
    }

    /**
     * Verifica se {@link Customer#getUsername()} já existe no banco de dados
     *
     * @param customer que será buscado pelo {@link Customer#getUsername()}
     * @throws UsernameAlreadyInUseException caso 'username' já esteja em uso.
     */
    private void isValidUsername(Customer customer) throws UsernameAlreadyInUseException {
        Optional<Customer> optionalCustomer = repository.findByUsername(customer.getUsername());
        optionalCustomer.ifPresent(value -> {
            throw new UsernameAlreadyInUseException(value);
        });
    }

    /**
     * Verifica se {@link Customer#getCpf()} já existe no banco de dados
     *
     * @param customer que será buscado pelo {@link Customer#getCpf()}
     * @throws CpfAlreadyInUseException caso 'cpf' já esteja em uso.
     */
    private void isValidCpf(Customer customer) throws CpfAlreadyInUseException {
        Optional<Customer> optionalCustomer = repository.findByCpf(customer.getCpf());
        optionalCustomer.ifPresent(value -> {
            throw new CpfAlreadyInUseException(value);
        });
    }
}
