package com.macedocaio.clientmanager.exceptions.customer;

import com.macedocaio.clientmanager.entities.Customer;

public abstract class CustomerException extends RuntimeException {

    protected final Customer customer;

    public CustomerException(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
