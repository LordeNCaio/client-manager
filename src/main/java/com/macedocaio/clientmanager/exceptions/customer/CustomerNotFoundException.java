package com.macedocaio.clientmanager.exceptions.customer;

import com.macedocaio.clientmanager.entities.Customer;

import java.util.UUID;

public class CustomerNotFoundException extends CustomerException {
    private final UUID resourceId;

    public CustomerNotFoundException(Customer customer) {
        super(customer);
        this.resourceId = null;
    }

    public CustomerNotFoundException(UUID resourceId) {
        super(null);
        this.resourceId = resourceId;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder()
                .append("Couldn't find a Customer with given ResourceId [")
                .append(resourceId)
                .append("]!");
        return sb.toString();
    }
}
