package com.macedocaio.customermanager.exception.handlers;

import com.macedocaio.customermanager.entities.interfaces.Customer;
import com.macedocaio.customermanager.exceptions.ErrorMessage;
import com.macedocaio.customermanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.customer.CustomerNotFoundException;
import com.macedocaio.customermanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.handler.CustomerExceptionHandler;
import com.macedocaio.customermanager.mocks.CustomerEntityMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerExceptionHandlerUnitTests {

    @Spy
    private CustomerExceptionHandler customerExceptionHandler;

    private static Customer customer;

    @BeforeAll
    public static void beforeAll() {
        customer = CustomerEntityMock.createJohnDoe();
    }

    @Test
    public void should_Handle_Username_Already_In_Use_Exception() throws InterruptedException {
        UsernameAlreadyInUseException exception = new UsernameAlreadyInUseException(customer);

        String expected = exception.getMessage();
        ErrorMessage actual = customerExceptionHandler.handleUsernameAlreadyInUseException(exception);

        TimeUnit.MILLISECONDS.sleep(1);

        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
        assertTrue(actual.getTimestamp().isBefore(LocalDateTime.now()));
    }

    @Test
    public void should_Handle_Cpf_Already_In_Use_Exception() throws InterruptedException {
        CpfAlreadyInUseException exception = new CpfAlreadyInUseException(customer);

        String expected = exception.getMessage();
        ErrorMessage actual = customerExceptionHandler.handleCpfAlreadyInUseException(exception);

        TimeUnit.MILLISECONDS.sleep(1);

        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
        assertTrue(actual.getTimestamp().isBefore(LocalDateTime.now()));
    }

    @Test
    public void should_Handle_Customer_Not_Found_Exception() throws InterruptedException {
        CustomerNotFoundException exception = new CustomerNotFoundException(customer.getResourceId());

        String expected = exception.getMessage();
        ErrorMessage actual = customerExceptionHandler.handleCustomerNotFoundException(exception);

        TimeUnit.MILLISECONDS.sleep(1);

        assertNotNull(actual);
        assertEquals(expected, actual.getMessage());
        assertTrue(actual.getTimestamp().isBefore(LocalDateTime.now()));
    }
}
