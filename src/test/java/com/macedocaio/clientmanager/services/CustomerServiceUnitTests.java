package com.macedocaio.clientmanager.services;

import com.macedocaio.clientmanager.builders.CustomerBuilder;
import com.macedocaio.clientmanager.entities.Customer;
import com.macedocaio.clientmanager.repositories.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceUnitTests {

    @Spy
    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repository;

    private static UUID resourceId;
    private static Customer customer;

    @BeforeAll
    public static void beforeAll() {
        resourceId = UUID.randomUUID();
        customer = createNewCustomer();
    }

    @Test
    @Order(1)
    public void shouldCreateSingle() {
        when(repository.save(Mockito.any(Customer.class))).thenReturn(customer);
        Customer saved = service.createSingle(customer);

        assertNotNull(saved);
        assertEquals(saved, customer);
    }

    @Test
    @Order(2)
    public void shouldFindByResourceId() {
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);
        Customer found = service.findByResourceId(resourceId);

        assertNotNull(found);
        assertEquals(found, customer);
    }

    @Test
    @Order(3)
    public void shouldUpdateByResourceId() {
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(createNewCustomer());

        Customer found = service.findByResourceId(resourceId);
        found.setFirstname("Johnny");
        found.setLastname("Johnny");

        service.updateByResourceId(found.getResourceId(), found);

        verify(service, times(1)).updateByResourceId(found.getResourceId(), found);
    }

    @Test
    @Order(4)
    public void shouldDeleteByResourceId() {
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

        Customer found = service.findByResourceId(resourceId);

        service.deleteByResourceId(found.getResourceId());

        verify(service, times(1)).deleteByResourceId(found.getResourceId());
    }

    private static Customer createNewCustomer() {
        return CustomerBuilder.getBuilder()
                .withId(1L)
                .withResourceId(UUID.randomUUID())
                .withFirstname("John")
                .withLastname("Doe")
                .withBirthday(LocalDate.of(2001, 1, 1))
                .build();
    }
}
