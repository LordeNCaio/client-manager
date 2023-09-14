package com.macedocaio.customermanager.utils;

import com.macedocaio.customermanager.builders.CustomerBuilder;
import com.macedocaio.customermanager.entities.CustomerEntity;

import java.time.LocalDate;
import java.util.UUID;

public final class CustomerTestsUtils {

    public static CustomerEntity createJohnDoe() {
        return CustomerBuilder.getBuilder()
                .withId(1L)
                .withResourceId(UUID.randomUUID())
                .withUsername("john_doe_001")
                .withFirstname("John")
                .withLastname("Doe")
                .withCpf("778.585.598-69")
                .withBirthday(LocalDate.of(2001, 1, 1))
                .build();
    }

    public static CustomerEntity createJaneDoe() {
        return CustomerBuilder.getBuilder()
                .withId(2L)
                .withResourceId(UUID.randomUUID())
                .withUsername("jane_doe_001")
                .withFirstname("Jane")
                .withLastname("Doe")
                .withCpf("194.557.968-48")
                .withBirthday(LocalDate.of(2001, 1, 1))
                .build();
    }

    public static CustomerEntity createBabyDoe() {
        return CustomerBuilder.getBuilder()
                .withId(2L)
                .withResourceId(UUID.randomUUID())
                .withUsername("baby_doe_001")
                .withFirstname("Baby")
                .withLastname("Doe")
                .withCpf("213.176.718-80")
                .withBirthday(LocalDate.of(2020, 1, 1))
                .build();
    }
}
