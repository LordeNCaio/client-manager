package com.macedocaio.customermanager.exceptions.customer;

import com.macedocaio.customermanager.entities.CustomerEntity;

/**
 * @author caiom
 * Exceção que será lançada caso cliente tente se registrar com um Username já cadastrado no sistema
 */
public class UsernameAlreadyInUseException extends CustomerException {

    /**
     * {@inheritDoc}
     */
    public UsernameAlreadyInUseException(CustomerEntity customer) {
        super(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder()
                .append("The username [")
                .append(customer.getUsername())
                .append("] is already in use!");
        return sb.toString();
    }
}
