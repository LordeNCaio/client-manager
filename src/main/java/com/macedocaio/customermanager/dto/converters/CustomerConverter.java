package com.macedocaio.customermanager.dto.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macedocaio.customermanager.entities.interfaces.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiom
 * Classe utiliza para converter implementações de {@link Customer} para outra implementação
 */
public final class CustomerConverter {

    /**
     * Converte um objeto que implementa a interface {@link Customer}
     *
     * @param from    objeto que será convertida
     * @param toClass Novo formato da objeto
     * @param <F>     Tipo da objeto 'from'
     * @param <T>     Tipo do retorno e objeto 'toClass'
     * @return objeto convertida
     */
    public static <F extends Customer, T extends Customer> T convertFromTo(F from, Class<T> toClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        try {
            String json = mapper.writeValueAsString(from);
            return mapper.readValue(json, toClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converte uma lista de que implementa a interface {@link Customer}
     *
     * @param from    Lista de objetos que será convertida
     * @param toClass Novo formato da lista de objetos
     * @param <F>     Tipo da objeto 'from'
     * @param <T>     Tipo do retorno e objeto 'toClass'
     * @return Lista de objetos convertida
     */
    public static <F extends Customer, T extends Customer> List<T> convertListFromTo(List<F> from, Class<T> toClass) {
        List<T> customers = new ArrayList<>();

        for (Customer customer : from) {
            customers.add(convertFromTo(customer, toClass));
        }

        return customers;
    }
}
