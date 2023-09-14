package com.macedocaio.customermanager.exceptions.customer;

import com.macedocaio.customermanager.entities.CustomerEntity;

/**
 * @author caiom
 * Classe abstrata para a construção de todas as exceções relacionadas a entidade {@link CustomerEntity}
 */
public abstract class CustomerException extends RuntimeException {

    /**
     * Objeto {@link CustomerEntity} que causou a exceção
     */
    protected final CustomerEntity customer;

    /**
     * Construtor padrão para exceção
     * @param customer que causou a exceção
     */
    public CustomerException(CustomerEntity customer) {
        this.customer = customer;
    }

    /**
     * Retornar o objeto causado da exceção
     * @return  {@link #customer}
     */
    public CustomerEntity getCustomer() {
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
