package com.macedocaio.customermanager.services;

import com.macedocaio.customermanager.entities.Customer;
import com.macedocaio.customermanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.customer.CustomerNotFoundException;
import com.macedocaio.customermanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.customermanager.repositories.CustomerRepository;
import com.macedocaio.customermanager.utils.CustomerUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
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
        customer = CustomerUtils.createJohnDoe();
        resourceId = customer.getResourceId();
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
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(ofNullable(customer));
        Customer found = service.findByResourceId(resourceId);

        assertNotNull(found);
        assertEquals(found, customer);
    }

    @Test
    @Order(3)
    public void shouldUpdateByResourceId() {
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(ofNullable(customer));

        Customer found = service.findByResourceId(resourceId);
        found.setFirstname("Johnny");
        found.setLastname("Johnny");

        service.updateByResourceId(found.getResourceId(), found);

        verify(service, times(1)).updateByResourceId(found.getResourceId(), found);
    }

    @Test
    @Order(4)
    public void shouldDeleteByResourceId() {
        when(repository.findByResourceId(Mockito.any(UUID.class))).thenReturn(ofNullable(customer));

        Customer found = service.findByResourceId(resourceId);

        service.deleteByResourceId(found.getResourceId());

        verify(service, times(1)).deleteByResourceId(found.getResourceId());
    }

    @Test
    @Order(5)
    public void shouldThrowUsernameAlreadyInUseExceptionOnCreateSingle() {
        when(repository.findByUsername(any(String.class))).thenReturn(ofNullable(customer));

        Throwable throwable = assertThrowsExactly(UsernameAlreadyInUseException.class,
                () -> service.createSingle(customer));

        assertEquals(UsernameAlreadyInUseException.class, throwable.getClass());
    }

    @Test
    @Order(6)
    public void shouldThrowCpfAlreadyInUseExceptionOnCreateSingle() {
        when(repository.findByUsername(any(String.class))).thenReturn(empty());
        when(repository.findByCpf(any(String.class))).thenReturn(ofNullable(customer));

        Throwable throwable = assertThrowsExactly(CpfAlreadyInUseException.class,
                () -> service.createSingle(customer));

        assertEquals(CpfAlreadyInUseException.class, throwable.getClass());
    }

    @Test
    @Order(7)
    public void shouldThrowCustomerNotFoundExceptionOnFindByResourceId() {
        when(repository.findByResourceId(any(UUID.class))).thenReturn(empty());

        Throwable throwable = assertThrowsExactly(CustomerNotFoundException.class,
                () -> service.findByResourceId(resourceId));

        assertEquals(CustomerNotFoundException.class, throwable.getClass());
    }

    @Test
    @Order(8)
    public void shouldThrowCustomerNotFoundExceptionOnUpdateByResourceId() {
        when(repository.findByResourceId(any(UUID.class))).thenReturn(empty());

        Throwable throwable = assertThrowsExactly(CustomerNotFoundException.class,
                () -> service.updateByResourceId(resourceId, customer));

        assertEquals(CustomerNotFoundException.class, throwable.getClass());
    }

    @Test
    @Order(9)
    public void shouldThrowCustomerNotFoundExceptionOnDeleteByResourceId() {
        when(repository.findByResourceId(any(UUID.class))).thenReturn(empty());

        Throwable throwable = assertThrowsExactly(CustomerNotFoundException.class,
                () -> service.deleteByResourceId(resourceId));

        assertEquals(CustomerNotFoundException.class, throwable.getClass());
    }
}
