package com.macedocaio.clientmanager.builders;

import com.macedocaio.clientmanager.entities.Customer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerBuilderUnitTests {

    @Test
    @Order(1)
    public void shouldGetBuilderInstance() {
        CustomerBuilder builder = null;
        builder = CustomerBuilder.getBuilder();

        assertNotNull(builder, "Instância do Builder está nula!");
    }

    @Test
    @Order(2)
    public void shouldBuildEmptyCustomer() {
        Customer customer = CustomerBuilder.getBuilder().build();
        assertNotNull(customer, "Instância do Customer está nula!");
    }

    @Test
    @Order(3)
    public void shouldBuildCustomerWithFirstnameAndLastName() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withFirstname("Claro")
                .withLastname("TV");
        Customer customer = builder.build();

        assertNotNull(customer);
        assertNotNull(customer.getFirstname());
        assertNotNull(customer.getLastname());
    }

    @Test
    @Order(4)
    public void shouldBuildFullCustomer() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withId(1L)
                .withResourceId(UUID.randomUUID())
                .withFirstname("Claro")
                .withLastname("TV")
                .withBirthday(LocalDate.of(2008, 12, 16));
        Customer customer = builder.build();

        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertNotNull(customer.getResourceId());
        assertNotNull(customer.getFirstname());
        assertNotNull(customer.getLastname());
        assertNotNull(customer.getBirthday());
    }
}
