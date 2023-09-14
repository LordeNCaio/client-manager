package com.macedocaio.customermanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macedocaio.customermanager.controllers.CustomerController;
import com.macedocaio.customermanager.entities.CustomerEntity;
import com.macedocaio.customermanager.exceptions.ErrorMessage;
import com.macedocaio.customermanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.customer.CustomerNotFoundException;
import com.macedocaio.customermanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.customermanager.utils.CustomerTestsUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRoutesIntegrationTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    private static UUID resourceId;
    private static CustomerEntity customer;

    @BeforeAll
    public static void beforeAll() {
        customer = CustomerTestsUtils.createJohnDoe();
        resourceId = customer.getResourceId();
    }

    @Test
    @Order(1)
    public void shouldCallCreateSingleRoute() throws Exception {
        MockHttpServletRequestBuilder builder = getCreateSingleRoute(customer);

        mvc.perform(builder)
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(2)
    public void shouldReturnUsernameAlreadyInUseOnSingleRoute() throws Exception {
        UsernameAlreadyInUseException exception = new UsernameAlreadyInUseException(customer);

        MockHttpServletRequestBuilder builder = getCreateSingleRoute(customer);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.CONFLICT.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        ErrorMessage errorMessage = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertEquals(errorMessage.getMessage(), exception.getMessage());
    }

    @Test
    @Order(3)
    public void shouldReturnCpfAlreadyInUseOnSingleRoute() throws Exception {
        CpfAlreadyInUseException exception = new CpfAlreadyInUseException(customer);

        CustomerEntity localCustomer = CustomerTestsUtils.createJohnDoe();
        localCustomer.setUsername("littlejohn002");

        MockHttpServletRequestBuilder builder = getCreateSingleRoute(localCustomer);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.CONFLICT.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        ErrorMessage errorMessage = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertEquals(errorMessage.getMessage(), exception.getMessage());
    }

    @Test
    @Order(4)
    public void shouldFindSingleResourceId() throws Exception {
        MockHttpServletRequestBuilder builder = getFindSingleByResourceId(resourceId);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        CustomerEntity customer = mapper.readValue(result.getResponse().getContentAsString(), CustomerEntity.class);
        assertNotNull(customer);
    }

    @Test
    @Order(5)
    public void shouldUpdateSingleResourceId() throws Exception {
        CustomerEntity customer = CustomerTestsUtils.createJohnDoe();
        customer.setFirstname("Johnny");
        customer.setLastname("Knoxville");

        MockHttpServletRequestBuilder builder = getUpdateSingleByResourceId(resourceId);

        mvc.perform(builder)
                .andExpect(status().is(HttpStatus.ACCEPTED.value()))
                .andReturn();
    }

    @Test
    @Order(6)
    public void shouldReturnCustomerNotFoundOnFindSingleResourceId() throws Exception {
        UUID localResourceId = UUID.randomUUID();
        CustomerNotFoundException exception = new CustomerNotFoundException(localResourceId);

        MockHttpServletRequestBuilder builder = getFindSingleByResourceId(localResourceId);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        ErrorMessage errorMessage = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertEquals(errorMessage.getMessage(), exception.getMessage());
    }

    @Test
    @Order(7)
    public void shouldReturnCustomerNotFoundOnUpdateSingleResourceId() throws Exception {
        UUID localResourceId = UUID.randomUUID();
        CustomerNotFoundException exception = new CustomerNotFoundException(localResourceId);

        MockHttpServletRequestBuilder builder = getUpdateSingleByResourceId(localResourceId);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        ErrorMessage errorMessage = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertEquals(errorMessage.getMessage(), exception.getMessage());
    }

    @Test
    @Order(7)
    public void shouldReturnCustomerNotFoundOnDeleteSingleResourceId() throws Exception {
        UUID localResourceId = UUID.randomUUID();
        CustomerNotFoundException exception = new CustomerNotFoundException(localResourceId);

        MockHttpServletRequestBuilder builder = getDeleteSingleByResourceId(localResourceId);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        ErrorMessage errorMessage = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertEquals(errorMessage.getMessage(), exception.getMessage());
    }

    @Test
    @Order(100)
    public void shouldDeleteSingleResourceId() throws Exception {
        MockHttpServletRequestBuilder builder = getDeleteSingleByResourceId(resourceId);

        mvc.perform(builder)
                .andExpect(status().is(HttpStatus.ACCEPTED.value()))
                .andReturn();
    }

    private MockHttpServletRequestBuilder getCreateSingleRoute(CustomerEntity customer) throws Exception {
        return MockMvcRequestBuilders
                .post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(mapper.writeValueAsBytes(customer));
    }

    private MockHttpServletRequestBuilder getFindSingleByResourceId(UUID resourceId) {
        return MockMvcRequestBuilders
                .get(String.format("%s/%s", CustomerController.BASE_URL, resourceId))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8);
    }

    private MockHttpServletRequestBuilder getUpdateSingleByResourceId(UUID resourceId) throws Exception {
        return MockMvcRequestBuilders
                .put(String.format("%s/%s", CustomerController.BASE_URL, resourceId))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(customer))
                .characterEncoding(StandardCharsets.UTF_8);
    }

    private MockHttpServletRequestBuilder getDeleteSingleByResourceId(UUID resourceId) {
        return MockMvcRequestBuilders
                .delete(String.format("%s/%s", CustomerController.BASE_URL, resourceId))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8);
    }
}
