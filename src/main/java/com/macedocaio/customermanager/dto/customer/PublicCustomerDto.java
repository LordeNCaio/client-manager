package com.macedocaio.customermanager.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.macedocaio.customermanager.entities.interfaces.Customer;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicCustomerDto implements Customer {
    private UUID resourceId;
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private String cpf;

    public PublicCustomerDto() {
    }

    public PublicCustomerDto(UUID resourceId, String username, String firstname, String lastname, LocalDate birthday,
                             String cpf) {
        this.resourceId = resourceId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.cpf = cpf;
    }


    @Override
    @JsonIgnore
    public Long getId() {
        return null;
    }

    @Override
    public UUID getResourceId() {
        return resourceId;
    }

    @Override
    public String getUsername() {
        return username;
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
    public String getCpf() {
        return cpf;
    }

    @Override
    @JsonIgnore
    public void setId(Long id) {}

    @Override
    public void setResourceId(UUID resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

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
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicCustomerDto that = (PublicCustomerDto) o;

        if (!resourceId.equals(that.resourceId)) return false;
        if (!username.equals(that.username)) return false;
        return cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        int result = resourceId.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + cpf.hashCode();
        return result;
    }
}
