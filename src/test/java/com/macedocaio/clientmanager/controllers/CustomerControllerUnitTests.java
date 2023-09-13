package com.macedocaio.clientmanager.controllers;

import com.macedocaio.clientmanager.builders.CustomerBuilder;
import com.macedocaio.clientmanager.entities.Customer;
import com.macedocaio.clientmanager.services.CustomerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerControllerUnitTests {
    @Spy
    @InjectMocks
    private CustomerController controller;

    @Mock
    private CustomerService service;

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
        when(service.createSingle(Mockito.any(Customer.class))).thenReturn(customer);
        Customer saved = controller.createSingle(customer);

        assertNotNull(saved);
        assertEquals(saved, customer);
    }

    @Test
    @Order(2)
    public void shouldFindByResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);
        Customer found = controller.findByResourceId(resourceId);

        assertNotNull(found);
        assertEquals(found, customer);
    }

    @Test
    @Order(3)
    public void shouldUpdateByResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(createNewCustomer());

        Customer found = controller.findByResourceId(resourceId);
        found.setFirstname("Johnny");
        found.setLastname("Johnny");

        controller.updateByResourceId(found.getResourceId(), found);

        verify(controller, times(1)).updateByResourceId(found.getResourceId(), found);
    }

    @Test
    @Order(4)
    public void shouldDeleteByResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

        Customer found = controller.findByResourceId(resourceId);

        controller.deleteByResourceId(found.getResourceId());

        verify(controller, times(1)).deleteByResourceId(found.getResourceId());
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
