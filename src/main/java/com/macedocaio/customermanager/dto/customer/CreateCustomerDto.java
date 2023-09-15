package com.macedocaio.customermanager.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.macedocaio.customermanager.annotations.Cpf;
import com.macedocaio.customermanager.entities.interfaces.Customer;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"id", "resourceId"})
public class CreateCustomerDto implements Customer {

    private static final String USERNAME_REGEX = "[A-Za-z0-9_-]*";
    private static final String NAME_PART_REGEX = "[A-Za-z]*";
    private static final int USERNAME_MIN_CHARACTERS = 8;
    private static final int USERNAME_MAX_CHARACTERS = 16;
    private static final int NAME_PART_MIN_CHARACTERS = 1;
    private static final int NAME_PART_MAX_CHARACTERS = 128;
    private static final int CPF_REQUIRED_CHARACTERS = 11;

    @NotBlank(message = "Username can't be null or empty")
    @Size(min = USERNAME_MIN_CHARACTERS, max = USERNAME_MAX_CHARACTERS,
            message = "Username must have between 8 and 16 characters")
    @Pattern(regexp = USERNAME_REGEX, message = "Username accept only letters, numbers, minus and underscore")
    private String username;

    @NotBlank(message = "Firstname can't be null or empty")
    @Size(min = NAME_PART_MIN_CHARACTERS, max = NAME_PART_MAX_CHARACTERS,
            message = "Firstname must have between 1 and 128 characters")
    @Pattern(regexp = NAME_PART_REGEX, message = "Firstname accept only letters")
    private String firstname;

    @NotBlank(message = "Lastname can't be null or empty")
    @Size(min = NAME_PART_MIN_CHARACTERS, max = NAME_PART_MAX_CHARACTERS,
            message = "Lastname must have between 1 and 128 characters")
    @Pattern(regexp = NAME_PART_REGEX, message = "Lastname accept only letters")
    private String lastname;

    @NotNull(message = "Birthday can't be null or empty")
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;

    @NotBlank(message = "CPF can't be null or empty")
    @Size(min = CPF_REQUIRED_CHARACTERS, max = CPF_REQUIRED_CHARACTERS,
            message = "Cpf must have 11 numeric characters")
    @Cpf(message = "Insert a valid CPF number")
    private String cpf;

    public CreateCustomerDto() {
    }

    public CreateCustomerDto(String username, String firstname, String lastname, LocalDate birthday, String cpf) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.cpf = cpf;
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
    public void setId(Long id) {}

    @Override
    public void setResourceId(UUID resourceId) {}

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
}
