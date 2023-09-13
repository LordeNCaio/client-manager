package com.macedocaio.clientmanager.services;

import com.macedocaio.clientmanager.entities.Customer;
import com.macedocaio.clientmanager.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer createSingle(Customer customer) {
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
        repository.save(customer);
    }

    public void deleteByResourceId(UUID resourceId) {
        Customer searched = repository.findByResourceId(resourceId);
        if (searched == null) {
            throw new RuntimeException("Customer doesn't exists!");
        }
        repository.deleteByResourceId(resourceId);
    }
}
