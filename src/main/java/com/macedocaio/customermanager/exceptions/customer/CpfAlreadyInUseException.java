package com.macedocaio.customermanager.exceptions.customer;

import com.macedocaio.customermanager.entities.CustomerEntity;

/**
 * @author caiom
 * Exceção que será lançada caso cliente tente se registrar com um CPF já cadastrado no sistema
 */
public class CpfAlreadyInUseException extends CustomerException {

    /**
     * {@inheritDoc}
     */
    public CpfAlreadyInUseException(CustomerEntity customer) {
        super(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder()
                .append("The CPF [")
                .append(customer.getCpf())
                .append("] is already in use!");
        return sb.toString();
    }
}
