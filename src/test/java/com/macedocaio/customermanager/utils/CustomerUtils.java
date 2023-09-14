package com.macedocaio.customermanager.utils;

import com.macedocaio.customermanager.builders.CustomerBuilder;
import com.macedocaio.customermanager.entities.Customer;

import java.time.LocalDate;
import java.util.UUID;

public final class CustomerUtils {

    public static Customer createJohnDoe() {
        return CustomerBuilder.getBuilder()
                .withId(1L)
                .withResourceId(UUID.randomUUID())
                .withUsername("littlejohn001")
                .withFirstname("John")
                .withLastname("Doe")
                .withCpf("778.585.598-69")
                .withBirthday(LocalDate.of(2001, 1, 1))
                .build();
    }
}
