package com.macedocaio.customermanager.services;

import com.macedocaio.customermanager.entities.Customer;
import com.macedocaio.customermanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.customer.CustomerNotFoundException;
import com.macedocaio.customermanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.customermanager.repositories.CustomerRepository;
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
        isValidUsername(customer);
        isValidCpf(customer);
        return repository.save(customer);
    }

    public Customer findByResourceId(UUID resourceId) {
        Optional<Customer> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw  new CustomerNotFoundException(resourceId);
        }

        return optionalCustomer.get();
    }

    public void updateByResourceId(UUID resourceId, Customer customer) {
        Optional<Customer> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw  new CustomerNotFoundException(resourceId);
        }

        repository.updateByResourceId(resourceId, customer);
    }

    public void deleteByResourceId(UUID resourceId) {
        Optional<Customer> optionalCustomer = repository.findByResourceId(resourceId);
        if (optionalCustomer.isEmpty()) {
            throw  new CustomerNotFoundException(resourceId);
        }

        repository.deleteByResourceId(resourceId);
    }

    private void isValidUsername(Customer customer) {
        Optional<Customer> optionalCustomer = repository.findByUsername(customer.getUsername());
        optionalCustomer.ifPresent(value -> {
            throw new UsernameAlreadyInUseException(value);
        });
    }

    private void isValidCpf(Customer customer) {
        Optional<Customer> optionalCustomer = repository.findByCpf(customer.getCpf());
        optionalCustomer.ifPresent(value -> {
            throw new CpfAlreadyInUseException(value);
        });
    }
}
