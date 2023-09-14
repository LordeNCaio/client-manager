package com.macedocaio.customermanager.builders;

import com.macedocaio.customermanager.entities.CustomerEntity;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author caiom
 * Classe utilizada para a construção de entidade do {@link CustomerEntity}
 * com parametros customizados
 */
public class CustomerBuilder {

    /**
     * Entidade {@link CustomerEntity} que será retornada pela função {@link #build()}
     */
    private final CustomerEntity customer;

    private CustomerBuilder() {
        customer = new CustomerEntity();
    }

    public static CustomerBuilder getBuilder() {
        return new CustomerBuilder();
    }

    /**
     * Aplica um novo valor para a {@link CustomerEntity#getId()} da entidade
     *
     * @param id Valor que será atribuído para o campo 'id' utilizando {@link CustomerEntity#setId(Long)}
     *           da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withId(Long id) {
        customer.setId(id);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link CustomerEntity#getResourceId()} da entidade
     *
     * @param resourceId Valor que será atribuído para o campo 'resourceId' utilizando
     *                   {@link CustomerEntity#setResourceId(UUID)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withResourceId(UUID resourceId) {
        customer.setResourceId(resourceId);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link CustomerEntity#getUsername()} da entidade
     *
     * @param username Valor que será atribuído para o campo 'username' utilizando
     *                 {@link CustomerEntity#setUsername(String)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withUsername(String username) {
        customer.setUsername(username);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link CustomerEntity#getFirstname()} da entidade
     *
     * @param firstname Valor que será atribuído para o campo 'firstname' utilizando
     *                  {@link CustomerEntity#setFirstname(String)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withFirstname(String firstname) {
        customer.setFirstname(firstname);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link CustomerEntity#getLastname()} da entidade
     *
     * @param lastname Valor que será atribuído para o campo 'lastname' utilizando
     *                 {@link CustomerEntity#setLastname(String)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withLastname(String lastname) {
        customer.setLastname(lastname);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link CustomerEntity#getBirthday()} da entidade
     *
     * @param birthday Valor que será atribuído para o campo 'birthday' utilizando
     *                 {@link CustomerEntity#setBirthday(LocalDate)} da entidade {@link #customer} instanciada pelo
     *                 construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withBirthday(LocalDate birthday) {
        customer.setBirthday(birthday);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link CustomerEntity#getCpf()} da entidade
     *
     * @param cpf Valor que será atribuído para o campo 'cpf' utilizando
     *            {@link CustomerEntity#setCpf(String)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withCpf(String cpf) {
        customer.setCpf(cpf);
        return this;
    }

    /**
     * Constrói o objeto do tipo {@link CustomerEntity}
     *
     * @return Entidade {@link #customer} instanciada pelo construtor do builder
     * com os parâmetros passados pelas funções do builder
     */
    public CustomerEntity build() {
        return customer;
    }
}
