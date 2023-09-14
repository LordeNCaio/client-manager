package com.macedocaio.clientmanager.services;

import com.macedocaio.clientmanager.entities.Customer;
import com.macedocaio.clientmanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.clientmanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.clientmanager.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer createSingle(Customer customer) {
        Optional<Customer> optionalCustomer = repository.findByUsername(customer.getUsername());
        optionalCustomer.ifPresent(value -> {
            throw new UsernameAlreadyInUseException(value);
        });

        optionalCustomer = repository.findByCpf(customer.getCpf());
        optionalCustomer.ifPresent(value -> {
            throw new CpfAlreadyInUseException(value);
        });

        return repository.save(customer);
    }

    public Customer findByResourceId(UUID resourceId) {
        return repository.findByResourceId(resourceId);
    }

    public void updateByResourceId(UUID resourceId, Customer customer) {
        Customer searched = repository.findByResourceId(resourceId);
        if (searched == null) {
            throw new RuntimeException("Customer doesn't exists!");
        }

        repository.updateByResourceId(resourceId, customer);
    }

    public void deleteByResourceId(UUID resourceId) {
        Customer searched = repository.findByResourceId(resourceId);
        if (searched == null) {
            throw new RuntimeException("Customer doesn't exists!");
        }
        repository.deleteByResourceId(resourceId);
    }
}
