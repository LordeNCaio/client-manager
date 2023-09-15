package com.macedocaio.customermanager.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.macedocaio.customermanager.entities.interfaces.Customer;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"id", "resourceId", "username", "cpf"})
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
    public Long getId() {
        return null;
    }

    @Override
    public UUID getResourceId() {
        return null;
    }

    @Override
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateCustomerDto that = (UpdateCustomerDto) o;

        if (!firstname.equals(that.firstname)) return false;
        if (!lastname.equals(that.lastname)) return false;
        return birthday.equals(that.birthday);
    }

    @Override
    public int hashCode() {
        int result = firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UpdateCustomerDto{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
