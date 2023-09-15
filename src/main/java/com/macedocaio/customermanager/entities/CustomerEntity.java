package com.macedocaio.customermanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.macedocaio.customermanager.annotations.Cpf;
import com.macedocaio.customermanager.entities.interfaces.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "CUSTOMERS")
public class CustomerEntity implements Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "UUID")
    private UUID resourceId = UUID.randomUUID();

    @NotBlank(message = "Username can't be null or empty")
    @Size(min = 8, max = 16, message = "Username must have between 4 and 16 characters")
    @Pattern(regexp = "[A-Za-z0-9_-]{4,16}", message = "Username accept only letters, numbers, minus and underscore")
    @Column(nullable = false, unique = true, length = 16)
    private String username;

    @NotBlank(message = "Firstname can't be null or empty")
    @Size(min = 1, max = 128, message = "Firstname must have between 1 and 128 characters")
    @Pattern(regexp = "[A-Za-z]{1,128}", message = "Firstname accept only letters")
    @Column(nullable = false, length = 128)
    private String firstname;

    @NotBlank(message = "Lastname can't be null or empty")
    @Size(min = 1, max = 128, message = "Lastname must have between 1 and 128 characters")
    @Pattern(regexp = "[A-Za-z]{1,128}", message = "Lastname accept only letters")
    @Column(nullable = false)
    private String lastname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @Past(message = "Birthday must be in the past")
    @Column(nullable = false)
    private LocalDate birthday;

    @Size(max = 11)
    @Column(nullable = false, unique = true, length = 11)
    @Cpf(message = "Insert a valid CPF number")
    private String cpf;

    public CustomerEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity customer = (CustomerEntity) o;

        if (!id.equals(customer.id)) return false;
        if (!resourceId.equals(customer.resourceId)) return false;
        return cpf.equals(customer.cpf);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + resourceId.hashCode();
        result = 31 * result + cpf.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    @Override
    public Long getId() {
        return id;
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
    public void setId(Long id) {
        this.id = id;
    }

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
}
