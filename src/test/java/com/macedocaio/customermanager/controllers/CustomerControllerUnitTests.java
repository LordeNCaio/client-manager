package com.macedocaio.customermanager.controllers;

import com.macedocaio.customermanager.dto.converters.CustomerConverter;
import com.macedocaio.customermanager.dto.customer.CreateCustomerDto;
import com.macedocaio.customermanager.dto.customer.PublicCustomerDto;
import com.macedocaio.customermanager.dto.customer.UpdateCustomerDto;
import com.macedocaio.customermanager.entities.CustomerEntity;
import com.macedocaio.customermanager.mocks.CustomerEntityMock;
import com.macedocaio.customermanager.services.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
public class CustomerControllerUnitTests {

    @Spy
    @InjectMocks
    private CustomerController controller;

    @Mock
    private CustomerService service;

    private static CustomerEntity customer;

    @BeforeAll
    public static void beforeAll() {
        customer = CustomerEntityMock.createJohnDoe();
    }

    @Test
    public void should_Create_Single() {
        when(service.createSingle(Mockito.any(CreateCustomerDto.class))).thenReturn(customer);

        PublicCustomerDto expected = CustomerConverter.convertFromTo(customer, PublicCustomerDto.class);
        CreateCustomerDto dto = CustomerConverter.convertFromTo(customer, CreateCustomerDto.class);

        PublicCustomerDto actual = controller.createSingle(dto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void should_Find_By_ResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

        PublicCustomerDto expected = CustomerConverter.convertFromTo(customer, PublicCustomerDto.class);
        PublicCustomerDto actual = controller.findByResourceId(customer.getResourceId());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void should_Find_All() {
        List<CustomerEntity> customers = Arrays.asList(CustomerEntityMock.createJohnDoe(), CustomerEntityMock.createJaneDoe(),
                CustomerEntityMock.createBabyDoe());

        when(service.findAll()).thenReturn(customers);
        List<PublicCustomerDto> foundedCustomers = controller.findAll();

        assertNotNull(foundedCustomers);
        assertFalse(foundedCustomers.isEmpty());
        assertEquals(customers.size(), foundedCustomers.size());
    }

    @Test
    public void should_Update_By_ResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

        PublicCustomerDto expected = controller.findByResourceId(customer.getResourceId());
        expected.setFirstname("Johnny");
        expected.setLastname("Johnny");

        UpdateCustomerDto updated = CustomerConverter.convertFromTo(expected, UpdateCustomerDto.class);

        controller.updateByResourceId(expected.getResourceId(), updated);

        verify(controller, times(1)).updateByResourceId(expected.getResourceId(), updated);
    }

    @Test
    public void should_Delete_By_ResourceId() {
        when(service.findByResourceId(Mockito.any(UUID.class))).thenReturn(customer);

        PublicCustomerDto actual = controller.findByResourceId(customer.getResourceId());
        controller.deleteByResourceId(actual.getResourceId());

        verify(controller, times(1)).deleteByResourceId(actual.getResourceId());
    }
}
