package com.macedocaio.customermanager.exceptions.customer;

import com.macedocaio.customermanager.entities.Customer;

/**
 * @author caiom
 * Classe abstrata para a construção de todas as exceções relacionadas a entidade {@link Customer}
 */
public abstract class CustomerException extends RuntimeException {

    /**
     * Objeto {@link Customer} que causou a exceção
     */
    protected final Customer customer;

    /**
     * Construtor padrão para exceção
     * @param customer que causou a exceção
     */
    public CustomerException(Customer customer) {
        this.customer = customer;
    }

    /**
     * Retornar o objeto causado da exceção
     * @return  {@link #customer}
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Método utilizado para customizar a mensagem de erro da exceção
     * @return Mensagem de erro customizada
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
