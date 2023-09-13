package com.macedocaio.clientmanager.controllers;

import com.macedocaio.clientmanager.entities.Customer;
import com.macedocaio.clientmanager.services.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public Customer createSingle(@RequestBody Customer customer) {
        return service.createSingle(customer);
    }

    @GetMapping(value = "/{resourceId}", headers = {HttpHeaders.CONTENT_TYPE})
    @ResponseStatus(HttpStatus.FOUND)
    public Customer findByResourceId(@PathVariable UUID resourceId) {
        return new Customer();
    }

    @PutMapping("/{resourceId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateByResourceId(@PathVariable UUID resourceId, @RequestBody Customer customer) {
        service.updateByResourceId(resourceId, customer);
    }

    @Transactional
    @DeleteMapping("/{resourceId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteByResourceId(@PathVariable UUID resourceId) {
        service.deleteByResourceId(resourceId);
    }
}
