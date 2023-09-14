package com.macedocaio.customermanager.controllers;

import com.macedocaio.customermanager.entities.Customer;
import com.macedocaio.customermanager.services.CustomerService;
import com.macedocaio.customermanager.utils.CustomerUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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
        customer = CustomerUtils.createJohnDoe();
        resourceId = customer.getResourceId();
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
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

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
}
