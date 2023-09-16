package com.macedocaio.customermanager.controllers;

import com.macedocaio.customermanager.dto.converters.CustomerConverter;
import com.macedocaio.customermanager.dto.customer.CreateCustomerDto;
import com.macedocaio.customermanager.dto.customer.PublicCustomerDto;
import com.macedocaio.customermanager.dto.customer.UpdateCustomerDto;
import com.macedocaio.customermanager.entities.CustomerEntity;
import com.macedocaio.customermanager.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author caiom
 * Classe responsavel pela disponibilização de rotas
 */
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/customers";

    public final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicCustomerDto createSingle(@Valid @RequestBody CreateCustomerDto dto) {
        CustomerEntity saved = service.createSingle(dto);
        return CustomerConverter.convertFromTo(saved, PublicCustomerDto.class);
    }

    @GetMapping(value = "/{resourceId}", headers = {HttpHeaders.CONTENT_TYPE})
    @ResponseStatus(HttpStatus.FOUND)
    public PublicCustomerDto findByResourceId(@PathVariable UUID resourceId) {
        CustomerEntity found = service.findByResourceId(resourceId);
        return CustomerConverter.convertFromTo(found, PublicCustomerDto.class);
    }

    @GetMapping(headers = {HttpHeaders.CONTENT_TYPE})
    @ResponseStatus(HttpStatus.FOUND)
    public List<PublicCustomerDto> findAll() {
        List<CustomerEntity> customers = service.findAll();
        return CustomerConverter.convertListFromTo(customers, PublicCustomerDto.class);
    }

    @Transactional
    @PutMapping("/{resourceId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateByResourceId(@PathVariable UUID resourceId, @RequestBody UpdateCustomerDto customer) {
        service.updateByResourceId(resourceId, customer);
    }

    @Transactional
    @DeleteMapping("/{resourceId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteByResourceId(@PathVariable UUID resourceId) {
        service.deleteByResourceId(resourceId);
    }
}
