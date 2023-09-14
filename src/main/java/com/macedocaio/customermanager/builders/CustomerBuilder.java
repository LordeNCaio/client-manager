package com.macedocaio.customermanager.builders;

import com.macedocaio.customermanager.entities.Customer;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author caiom
 * Classe utilizada para a construção de entidade do {@link Customer}
 * com parametros customizados
 */
public class CustomerBuilder {

    /**
     * Entidade {@link Customer} que será retornada pela função {@link #build()}
     */
    private final Customer customer;

    private CustomerBuilder() {
        customer = new Customer();
    }

    public static CustomerBuilder getBuilder() {
        return new CustomerBuilder();
    }

    /**
     * Aplica um novo valor para a {@link Customer#getId()} da entidade
     *
     * @param id Valor que será atribuído para o campo 'id' utilizando {@link Customer#setId(Long)}
     *           da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withId(Long id) {
        customer.setId(id);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link Customer#getResourceId()} da entidade
     *
     * @param resourceId Valor que será atribuído para o campo 'resourceId' utilizando
     *                   {@link Customer#setResourceId(UUID)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withResourceId(UUID resourceId) {
        customer.setResourceId(resourceId);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link Customer#getUsername()} da entidade
     *
     * @param username Valor que será atribuído para o campo 'username' utilizando
     *                 {@link Customer#setUsername(String)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withUsername(String username) {
        customer.setUsername(username);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link Customer#getFirstname()} da entidade
     *
     * @param firstname Valor que será atribuído para o campo 'firstname' utilizando
     *                  {@link Customer#setFirstname(String)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withFirstname(String firstname) {
        customer.setFirstname(firstname);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link Customer#getLastname()} da entidade
     *
     * @param lastname Valor que será atribuído para o campo 'lastname' utilizando
     *                 {@link Customer#setLastname(String)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withLastname(String lastname) {
        customer.setLastname(lastname);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link Customer#getBirthday()} da entidade
     *
     * @param birthday Valor que será atribuído para o campo 'birthday' utilizando
     *                 {@link Customer#setBirthday(LocalDate)} da entidade {@link #customer} instanciada pelo
     *                 construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withBirthday(LocalDate birthday) {
        customer.setBirthday(birthday);
        return this;
    }

    /**
     * Aplica um novo valor para a {@link Customer#getCpf()} da entidade
     *
     * @param cpf Valor que será atribuído para o campo 'cpf' utilizando
     *            {@link Customer#setCpf(String)} da entidade {@link #customer} instanciada pelo construtor.
     * @return O próprio {@link CustomerBuilder}
     */
    public CustomerBuilder withCpf(String cpf) {
        customer.setCpf(cpf);
        return this;
    }

    /**
     * Constrói o objeto do tipo {@link Customer}
     *
     * @return Entidade {@link #customer} instanciada pelo construtor do builder
     * com os parâmetros passados pelas funções do builder
     */
    public Customer build() {
        return customer;
    }
}
