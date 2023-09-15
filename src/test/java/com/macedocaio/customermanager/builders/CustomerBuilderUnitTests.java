package com.macedocaio.customermanager.builders;

import com.macedocaio.customermanager.entities.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerBuilderUnitTests {

    private final Long id = 1L;
    private final UUID resourceId = UUID.randomUUID();
    private final String username = "clarotv10";
    private final String firstname = "Claro";
    private final String lastname = "TV";
    private final LocalDate birthday = LocalDate.of(2008, 12, 16);
    private final String cpf = "32673503870";

    @Test
    public void should_1Get_1Builder_1Instance() {
        CustomerBuilder builder = CustomerBuilder.getBuilder();
        assertNotNull(builder, "Builder instance is null!");
    }

    @Test
    public void should_1Build_1Empty_1Customer() {
        CustomerEntity customer = CustomerBuilder.getBuilder().build();
        assertNotNull(customer, "Customer instance is null!");
    }

    @Test
    public void should_1Build_1Customer_1With_1Id() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withId(id);
        CustomerEntity customer = builder.build();

        assertNotNull(customer, "Customer instance is null!");
        assertNotNull(customer.getId(), "Customer Id instance is null!");
        assertEquals(customer.getId(), id, "Customer Id is not equal to " + id);
    }

    @Test
    public void should_1Build_1Customer_1With_1ResourceId() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withResourceId(resourceId);
        CustomerEntity customer = builder.build();

        assertNotNull(customer, "Customer instance is null!");
        assertNotNull(customer.getResourceId(), "Customer ResourceId instance is null!");
        assertEquals(customer.getResourceId(), resourceId, "Customer ResourceId is not equal to " + resourceId);
    }

    @Test
    public void should_1Build_1Customer_1With_1Username() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withUsername(username);
        CustomerEntity customer = builder.build();

        assertNotNull(customer, "Customer instance is null!");
        assertNotNull(customer.getUsername(), "Customer Username instance is null!");
        assertEquals(customer.getUsername(), username, "Customer Username is not equal to " + username);
    }

    @Test
    public void should_1Build_1Customer_1With_1Firstname() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withFirstname(firstname);
        CustomerEntity customer = builder.build();

        assertNotNull(customer, "Customer instance is null!");
        assertNotNull(customer.getFirstname(), "Customer Firstname instance is null!");
        assertEquals(customer.getFirstname(), firstname, "Customer Firstname is not equal to " + firstname);
    }

    @Test
    public void should_1Build_1Customer_1With_1Lastname() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withLastname(lastname);
        CustomerEntity customer = builder.build();

        assertNotNull(customer, "Customer instance is null!");
        assertNotNull(customer.getLastname(), "Customer Lastname instance is null!");
        assertEquals(customer.getLastname(), lastname, "Customer Lastname is not equal to " + lastname);
    }

    @Test
    public void should_1Build_1Customer_1With_1Birthday() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withBirthday(birthday);
        CustomerEntity customer = builder.build();

        assertNotNull(customer, "Customer instance is null!");
        assertNotNull(customer.getBirthday(), "Customer Birthday instance is null!");
        assertEquals(customer.getBirthday(), birthday, "Customer Birthday is not equal to " + birthday);
    }

    @Test
    public void should_1Build_1Customer_1With_1Cpf() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withCpf(cpf);
        CustomerEntity customer = builder.build();

        assertNotNull(customer, "Customer instance is null!");
        assertNotNull(customer.getCpf(), "Customer Cpf instance is null!");
        assertEquals(customer.getCpf(), cpf, "Customer Cpf is not equal to " + cpf);
    }

    @Test
    public void should_1Build_1Full_1Customer() {
        CustomerBuilder builder = CustomerBuilder.getBuilder()
                .withId(id)
                .withResourceId(resourceId)
                .withUsername(username)
                .withFirstname(firstname)
                .withLastname(lastname)
                .withBirthday(birthday)
                .withCpf(cpf);
        CustomerEntity customer = builder.build();

        assertNotNull(customer, "Customer instance is null!");
        assertEquals(customer.getId(), id, "Customer Id is not equal to " + id);
        assertEquals(customer.getResourceId(), resourceId, "Customer resourceId is not equal to " + resourceId);
        assertEquals(customer.getUsername(), username, "Customer Username is not equal to " + username);
        assertEquals(customer.getFirstname(), firstname, "Customer Firstname is not equal to " + firstname);
        assertEquals(customer.getLastname(), lastname, "Customer Lastname is not equal to " + lastname);
        assertEquals(customer.getBirthday(), birthday, "Customer Birthday is not equal to " + birthday);
        assertEquals(customer.getCpf(), cpf, "Customer Cpf is not equal to " + cpf);
    }
}
