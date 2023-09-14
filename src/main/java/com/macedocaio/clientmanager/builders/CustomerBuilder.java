package com.macedocaio.clientmanager.builders;

import com.macedocaio.clientmanager.entities.Customer;

import java.time.LocalDate;
import java.util.UUID;

public class CustomerBuilder {
    private final Customer customer;

    private CustomerBuilder() {
        customer = new Customer();
    }

    public static CustomerBuilder getBuilder() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withId(Long id) {
        customer.setId(id);
        return this;
    }

    public CustomerBuilder withResourceId(UUID resourceId) {
        customer.setResourceId(resourceId);
        return this;
    }

    public CustomerBuilder withUsername(String username) {
        customer.setUsername(username);
        return this;
    }

    public CustomerBuilder withFirstname(String firstname) {
        customer.setFirstname(firstname);
        return this;
    }

    public CustomerBuilder withLastname(String lastname) {
        customer.setLastname(lastname);
        return this;
    }

    public CustomerBuilder withBirthday(LocalDate birthday) {
        customer.setBirthday(birthday);
        return this;
    }

    public CustomerBuilder withCpf(String cpf) {
        customer.setCpf(cpf);
        return this;
    }

    public Customer build() {
        return customer;
    }
}
