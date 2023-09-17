package com.macedocaio.customermanager.mocks;

import com.macedocaio.customermanager.builders.CustomerBuilder;
import com.macedocaio.customermanager.entities.CustomerEntity;

import java.time.LocalDate;

public final class CustomerEntityMock {

    public static CustomerEntity createJohnDoe() {
        return CustomerBuilder.getBuilder()
                .withId(1L)
                .withUsername("john_doe_001")
                .withFirstname("John")
                .withLastname("Doe")
                .withCpf("77858559869")
                .withBirthday(LocalDate.of(2001, 1, 1))
                .build();
    }

    public static CustomerEntity createJaneDoe() {
        return CustomerBuilder.getBuilder()
                .withId(2L)
                .withUsername("jane_doe_001")
                .withFirstname("Jane")
                .withLastname("Doe")
                .withCpf("19455796848")
                .withBirthday(LocalDate.of(2001, 1, 1))
                .build();
    }

    public static CustomerEntity createBabyDoe() {
        return CustomerBuilder.getBuilder()
                .withId(2L)
                .withUsername("baby_doe_001")
                .withFirstname("Baby")
                .withLastname("Doe")
                .withCpf("21317671880")
                .withBirthday(LocalDate.of(2020, 1, 1))
                .build();
    }
}
