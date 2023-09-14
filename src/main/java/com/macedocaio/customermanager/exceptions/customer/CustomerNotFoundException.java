package com.macedocaio.customermanager.exceptions.customer;

import com.macedocaio.customermanager.entities.Customer;

import java.util.UUID;

/**
 * @author caiom
 * Exceção que será lançada caso não seja encontrado nenhum {@link Customer}
 */
public class CustomerNotFoundException extends CustomerException {

    /**
     * {@link UUID} utilizado na busca do {@link Customer}
     */
    private final UUID resourceId;

    /**
     * Overload do construtor padrão para receber {@link UUID}
     * @param resourceId utilizado na busca do {@link Customer}
     */
    public CustomerNotFoundException(UUID resourceId) {
        super(null);
        this.resourceId = resourceId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder()
                .append("Couldn't find a Customer with given ResourceId [")
                .append(resourceId)
                .append("]!");
        return sb.toString();
    }
}
