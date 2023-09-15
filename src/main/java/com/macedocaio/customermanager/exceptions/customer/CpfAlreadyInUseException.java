package com.macedocaio.customermanager.exceptions.customer;

import com.macedocaio.customermanager.entities.CustomerEntity;
import com.macedocaio.customermanager.entities.interfaces.Customer;

/**
 * @author caiom
 * Exceção que será lançada caso cliente tente se registrar com um CPF já cadastrado no sistema
 */
public class CpfAlreadyInUseException extends CustomerException {

    /**
     * {@inheritDoc}
     */
    public CpfAlreadyInUseException(Customer customer) {
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
