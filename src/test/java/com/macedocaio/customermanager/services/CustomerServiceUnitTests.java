package com.macedocaio.customermanager.services;

import com.macedocaio.customermanager.dto.converters.CustomerConverter;
import com.macedocaio.customermanager.dto.customer.UpdateCustomerDto;
import com.macedocaio.customermanager.entities.CustomerEntity;
import com.macedocaio.customermanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.customer.CustomerNotFoundException;
import com.macedocaio.customermanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.customermanager.repositories.CustomerRepository;
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

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceUnitTests {

    @Spy
    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repository;

    private static UUID resourceId;

    private static CustomerEntity customer;

    @BeforeAll
    public static void beforeAll() {
        customer = CustomerTestsUtils.createJohnDoe();
        resourceId = customer.getResourceId();
    }

    @Test
    public void shouldCreateSingle() {
        when(repository.save(Mockito.any(CustomerEntity.class))).thenReturn(customer);
        CustomerEntity saved = service.createSingle(customer);

        assertNotNull(saved);
        assertEquals(saved, customer);
    }

    @Test
    public void shouldFindByResourceId() {
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(ofNullable(customer));
        CustomerEntity found = service.findByResourceId(resourceId);

        assertNotNull(found);
        assertEquals(found, customer);
    }

    @Test
    public void shouldFindByAll() {
        List<CustomerEntity> customers = Arrays.asList(CustomerTestsUtils.createJohnDoe(), CustomerTestsUtils.createJaneDoe(),
                CustomerTestsUtils.createBabyDoe());

        when(repository.findAll()).thenReturn(customers);
        List<CustomerEntity> foundedCustomers = service.findAll();

        assertNotNull(foundedCustomers);
        assertEquals(foundedCustomers, customers);
        assertFalse(foundedCustomers.isEmpty());
    }

    @Test
    public void shouldUpdateByResourceId() {
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(ofNullable(customer));

        CustomerEntity expected = service.findByResourceId(resourceId);
        expected.setFirstname("Johnny");
        expected.setLastname("Johnny");

        UpdateCustomerDto updated = CustomerConverter.convertFromTo(expected, UpdateCustomerDto.class);

        service.updateByResourceId(expected.getResourceId(), updated);

        verify(service, times(1)).updateByResourceId(expected.getResourceId(), updated);
    }

    @Test
    public void shouldDeleteByResourceId() {
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(ofNullable(customer));

        CustomerEntity found = service.findByResourceId(resourceId);

        service.deleteByResourceId(found.getResourceId());

        verify(service, times(1)).deleteByResourceId(found.getResourceId());
    }

    @Test
    public void shouldThrowUsernameAlreadyInUseExceptionOnCreateSingle() {
        when(repository.findByUsername(any(String.class))).thenReturn(ofNullable(customer));

        Throwable throwable = assertThrowsExactly(UsernameAlreadyInUseException.class,
                () -> service.createSingle(customer));

        assertEquals(UsernameAlreadyInUseException.class, throwable.getClass());
    }

    @Test
    public void shouldThrowCpfAlreadyInUseExceptionOnCreateSingle() {
        when(repository.findByUsername(any(String.class))).thenReturn(empty());
        when(repository.findByCpf(any(String.class))).thenReturn(ofNullable(customer));

        Throwable throwable = assertThrowsExactly(CpfAlreadyInUseException.class,
                () -> service.createSingle(customer));

        assertEquals(CpfAlreadyInUseException.class, throwable.getClass());
    }

    @Test
    public void shouldThrowCustomerNotFoundExceptionOnFindByResourceId() {
        when(repository.findByResourceId(any(UUID.class))).thenReturn(empty());

        Throwable throwable = assertThrowsExactly(CustomerNotFoundException.class,
                () -> service.findByResourceId(resourceId));

        assertEquals(CustomerNotFoundException.class, throwable.getClass());
    }

    @Test
    public void shouldThrowCustomerNotFoundExceptionOnUpdateByResourceId() {
        when(repository.findByResourceId(any(UUID.class))).thenReturn(empty());

        UpdateCustomerDto updated = CustomerConverter.convertFromTo(customer, UpdateCustomerDto.class);

        Throwable throwable = assertThrowsExactly(CustomerNotFoundException.class,
                () -> service.updateByResourceId(resourceId, updated));

        assertEquals(CustomerNotFoundException.class, throwable.getClass());
    }

    @Test
    public void shouldThrowCustomerNotFoundExceptionOnDeleteByResourceId() {
        when(repository.findByResourceId(any(UUID.class))).thenReturn(empty());

        Throwable throwable = assertThrowsExactly(CustomerNotFoundException.class,
                () -> service.deleteByResourceId(resourceId));

        assertEquals(CustomerNotFoundException.class, throwable.getClass());
    }
}
