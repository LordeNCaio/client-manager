package com.macedocaio.customermanager.entities;

import com.macedocaio.customermanager.entities.interfaces.Customer;
import jakarta.persistence.*;

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

    @Column(nullable = false, unique = true, length = 16)
    private String username;

    @Column(nullable = false, length = 128)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, unique = true, length = 11)
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
