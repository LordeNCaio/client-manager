package com.macedocaio.customermanager.exceptions.customer;

import com.macedocaio.customermanager.entities.Customer;

public class CpfAlreadyInUseException extends CustomerException {

    public CpfAlreadyInUseException(Customer customer) {
        super(customer);
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder()
                .append("The CPF [")
                .append(customer.getCpf())
                .append("] is already in use!");
        return sb.toString();
    }
}
