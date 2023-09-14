package com.macedocaio.customermanager.controllers;

import com.macedocaio.customermanager.controllers.CustomerController;
import com.macedocaio.customermanager.dto.converters.CustomerConverter;
import com.macedocaio.customermanager.dto.customer.PublicCustomerDto;
import com.macedocaio.customermanager.dto.customer.UpdateCustomerDto;
import com.macedocaio.customermanager.entities.CustomerEntity;
import com.macedocaio.customermanager.services.CustomerService;
import com.macedocaio.customermanager.utils.CustomerTestsUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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

    private static CustomerEntity customer;

    @BeforeAll
    public static void beforeAll() {
        customer = CustomerTestsUtils.createJohnDoe();
        resourceId = customer.getResourceId();
    }

    @Test
    @Order(1)
    public void shouldCreateSingle() {
        when(service.createSingle(Mockito.any(CustomerEntity.class))).thenReturn(customer);

        PublicCustomerDto expected = CustomerConverter.convertFromTo(customer, PublicCustomerDto.class);
        PublicCustomerDto actual = controller.createSingle(customer);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void shouldFindByResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

        PublicCustomerDto expected = CustomerConverter.convertFromTo(customer, PublicCustomerDto.class);
        PublicCustomerDto actual = controller.findByResourceId(resourceId);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void shouldFindAll() {
        List<CustomerEntity> customers = Arrays.asList(CustomerTestsUtils.createJohnDoe(), CustomerTestsUtils.createJaneDoe(),
                CustomerTestsUtils.createBabyDoe());

        when(service.findAll()).thenReturn(customers);
        List<PublicCustomerDto> foundedCustomers = controller.findAll();

        assertNotNull(foundedCustomers);
        assertFalse(foundedCustomers.isEmpty());
        assertEquals(customers.size(), foundedCustomers.size());
    }

    @Test
    @Order(4)
    public void shouldUpdateByResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

        PublicCustomerDto expected = controller.findByResourceId(resourceId);
        expected.setFirstname("Johnny");
        expected.setLastname("Johnny");

        UpdateCustomerDto updated = CustomerConverter.convertFromTo(expected, UpdateCustomerDto.class);

        controller.updateByResourceId(expected.getResourceId(), updated);

        verify(controller, times(1)).updateByResourceId(expected.getResourceId(), updated);
    }

    @Test
    @Order(5)
    public void shouldDeleteByResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

        PublicCustomerDto actual = controller.findByResourceId(resourceId);
        controller.deleteByResourceId(actual.getResourceId());

        verify(controller, times(1)).deleteByResourceId(actual.getResourceId());
    }
}
