package com.macedocaio.clientmanager.exceptions.customer;

import com.macedocaio.clientmanager.entities.Customer;

public class UsernameAlreadyInUseException extends CustomerException {

    public UsernameAlreadyInUseException(Customer customer) {
        super(customer);
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder()
                .append("The username [")
                .append(customer.getUsername())
                .append("] is already in use!");
        return sb.toString();
    }
}
