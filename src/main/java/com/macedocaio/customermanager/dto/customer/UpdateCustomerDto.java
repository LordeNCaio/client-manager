package com.macedocaio.customermanager.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.macedocaio.customermanager.entities.interfaces.Customer;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCustomerDto implements Customer {

    private String firstname;
    private String lastname;
    private LocalDate birthday;

    public UpdateCustomerDto() {
    }

    public UpdateCustomerDto(String firstname, String lastname, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    @Override
    @JsonIgnore
    public Long getId() {
        return null;
    }

    @Override
    @JsonIgnore
    public UUID getResourceId() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return null;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    @JsonIgnore
    public String getCpf() {
        return null;
    }

    @Override
    @JsonIgnore
    public void setId(Long id) {}

    @Override
    @JsonIgnore
    public void setResourceId(UUID resourceId) {}

    @Override
    @JsonIgnore
    public void setUsername(String username) {}

    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    @JsonIgnore
    public void setCpf(String cpf) {}
}
