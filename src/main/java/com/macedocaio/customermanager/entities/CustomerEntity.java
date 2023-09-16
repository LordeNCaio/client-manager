package com.macedocaio.customermanager.entities;

import com.macedocaio.customermanager.entities.interfaces.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = CustomerEntity.TABLE_NAME)
public class CustomerEntity implements Customer {

    public static final String TABLE_NAME = "CUSTOMERS";
    private static final String COLUMN_ID = "CUST_CD_CUSTOMER";
    private static final String COLUMN_RESOURCE_ID = "CUST_CD_UUID";
    private static final String COLUMN_USERNAME = "CUST_DS_USERNAME";
    private static final String COLUMN_FIRSTNAME = "CUST_DS_FIRSTNAME";
    private static final String COLUMN_LASTNAME = "CUST_DS_LASTNAME";
    private static final String COLUMN_BIRTHDAY = "CUST_DT_BIRTHDAY";
    private static final String COLUMN_CPF = "CUST_DS_CPF";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID, nullable = false, unique = true)
    private Long id;

    @Column(name = COLUMN_RESOURCE_ID, nullable = false, unique = true, columnDefinition = "UUID")
    private UUID resourceId = UUID.randomUUID();

    @Column(name = COLUMN_USERNAME, nullable = false, unique = true, length = 16)
    private String username;

    @Column(name = COLUMN_FIRSTNAME, nullable = false, length = 128)
    private String firstname;

    @Column(name = COLUMN_LASTNAME, nullable = false, length = 128)
    private String lastname;

    @Column(name = COLUMN_BIRTHDAY, nullable = false)
    private LocalDate birthday;

    @Column(name = COLUMN_CPF, nullable = false, unique = true, length = 11)
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
