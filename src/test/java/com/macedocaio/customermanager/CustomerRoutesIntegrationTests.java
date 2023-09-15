package com.macedocaio.customermanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macedocaio.customermanager.controllers.CustomerController;
import com.macedocaio.customermanager.dto.converters.CustomerConverter;
import com.macedocaio.customermanager.dto.customer.CreateCustomerDto;
import com.macedocaio.customermanager.dto.customer.PublicCustomerDto;
import com.macedocaio.customermanager.dto.customer.UpdateCustomerDto;
import com.macedocaio.customermanager.entities.CustomerEntity;
import com.macedocaio.customermanager.exceptions.ErrorMessage;
import com.macedocaio.customermanager.exceptions.customer.CpfAlreadyInUseException;
import com.macedocaio.customermanager.exceptions.customer.CustomerNotFoundException;
import com.macedocaio.customermanager.exceptions.customer.UsernameAlreadyInUseException;
import com.macedocaio.customermanager.mocks.CustomerTestsUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
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
public class CustomerRoutesIntegrationTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    private CreateCustomerDto createCustomerDto;

    @AfterEach
    public void afterEach() {
        createCustomerDto = null;
    }

    @Test
    public void should_Call_Create_Single_Route() throws Exception {
        CustomerEntity customer = CustomerTestsUtils.createJohnDoe();
        createCustomerDto = CustomerConverter.convertFromTo(customer, CreateCustomerDto.class);

        MockHttpServletRequestBuilder builder = getCreateSingleRoute(createCustomerDto);
        mvc.perform(builder)
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void should_Find_Single_By_ResourceId() throws Exception {
        CustomerEntity customer = CustomerTestsUtils.createJohnDoe();
        customer.setUsername("john_doe_002");
        customer.setCpf("95554736840");

        createCustomerDto = CustomerConverter.convertFromTo(customer, CreateCustomerDto.class);
        PublicCustomerDto publicCustomer = createCustomerForTest();

        MockHttpServletRequestBuilder builder = getFindSingleByResourceId(publicCustomer.getResourceId());

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        PublicCustomerDto publicCustomerDto = mapper.readValue(result.getResponse().getContentAsString(),
                PublicCustomerDto.class);
        assertNotNull(publicCustomerDto);
    }

    @Test
    public void should_Update_Single_By_ResourceId() throws Exception {
        CustomerEntity customer = CustomerTestsUtils.createJaneDoe();
        createCustomerDto = CustomerConverter.convertFromTo(customer, CreateCustomerDto.class);
        PublicCustomerDto publicCustomer = createCustomerForTest();

        UpdateCustomerDto updateCustomerDto = CustomerConverter.convertFromTo(customer, UpdateCustomerDto.class);
        updateCustomerDto.setFirstname("Johnny");
        updateCustomerDto.setLastname("Knoxville");

        MockHttpServletRequestBuilder builder = getUpdateSingleByResourceId(publicCustomer.getResourceId(),
                updateCustomerDto);

        mvc.perform(builder)
                .andExpect(status().is(HttpStatus.ACCEPTED.value()))
                .andReturn();
    }

    @Test
    public void should_Delete_Single_By_ResourceId() throws Exception {
        CustomerEntity customer = CustomerTestsUtils.createBabyDoe();
        createCustomerDto = CustomerConverter.convertFromTo(customer, CreateCustomerDto.class);
        PublicCustomerDto publicCustomer = createCustomerForTest();

        MockHttpServletRequestBuilder builder = getDeleteSingleByResourceId(publicCustomer.getResourceId());

        mvc.perform(builder)
                .andExpect(status().is(HttpStatus.ACCEPTED.value()))
                .andReturn();
    }

    @Test
    public void should_Throw_Username_Already_In_Use_On_Find_Single_By_ResourceId_Route() throws Exception {
        CustomerEntity customer = CustomerTestsUtils.createJaneDoe();
        customer.setUsername("jane_doe_002");
        customer.setCpf("20169650880");

        createCustomerDto = CustomerConverter.convertFromTo(customer, CreateCustomerDto.class);
        createCustomerForTest();

        UsernameAlreadyInUseException exception = new UsernameAlreadyInUseException(customer);
        MockHttpServletRequestBuilder builder = getCreateSingleRoute(createCustomerDto);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.CONFLICT.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        ErrorMessage errorMessage = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertEquals(errorMessage.getMessage(), exception.getMessage());
    }

    @Test
    public void should_Throw_Cpf_Already_In_Use_On_Find_Single_By_ResourceId_Route() throws Exception {
        CustomerEntity customer = CustomerTestsUtils.createJaneDoe();
        customer.setUsername("jane_doe_003");
        customer.setCpf("69917104828");

        createCustomerDto = CustomerConverter.convertFromTo(customer, CreateCustomerDto.class);
        createCustomerForTest();

        createCustomerDto.setUsername("jane_doe_004");

        CpfAlreadyInUseException exception = new CpfAlreadyInUseException(customer);
        MockHttpServletRequestBuilder builder = getCreateSingleRoute(createCustomerDto);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.CONFLICT.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        ErrorMessage errorMessage = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertEquals(errorMessage.getMessage(), exception.getMessage());
    }


    @Test
    public void should_Throw_Customer_Not_Found_On_Find_Single_By_ResourceId_Route() throws Exception {
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
    public void should_Throw_Customer_Not_Found_On_Find_Update_By_ResourceId_Route() throws Exception {
        UUID localResourceId = UUID.randomUUID();
        CustomerNotFoundException exception = new CustomerNotFoundException(localResourceId);

        MockHttpServletRequestBuilder builder = getUpdateSingleByResourceId(localResourceId, new UpdateCustomerDto());

        MvcResult result = mvc.perform(builder)
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        ErrorMessage errorMessage = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertEquals(errorMessage.getMessage(), exception.getMessage());
}

    @Test
    public void should_Throw_Customer_Not_Found_On_Delete_Single_By_ResourceId_Route() throws Exception {
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

    private MockHttpServletRequestBuilder getCreateSingleRoute(CreateCustomerDto createCustomerDto) throws Exception {
        return MockMvcRequestBuilders
                .post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(mapper.writeValueAsBytes(createCustomerDto));
    }

    private MockHttpServletRequestBuilder getFindSingleByResourceId(UUID resourceId) {
        return MockMvcRequestBuilders
                .get(String.format("%s/%s", CustomerController.BASE_URL, resourceId))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8);
    }

    private MockHttpServletRequestBuilder getUpdateSingleByResourceId(UUID resourceId,
                                                                      UpdateCustomerDto updateCustomerDto)
            throws Exception {
        return MockMvcRequestBuilders
                .put(String.format("%s/%s", CustomerController.BASE_URL, resourceId))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(updateCustomerDto))
                .characterEncoding(StandardCharsets.UTF_8);
    }

    private MockHttpServletRequestBuilder getDeleteSingleByResourceId(UUID resourceId) {
        return MockMvcRequestBuilders
                .delete(String.format("%s/%s", CustomerController.BASE_URL, resourceId))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8);
    }

    private PublicCustomerDto createCustomerForTest() throws Exception {
        return mapper.readValue(
                mvc.perform(getCreateSingleRoute(createCustomerDto)).andReturn().getResponse().getContentAsString(),
                PublicCustomerDto.class);
    }
}
