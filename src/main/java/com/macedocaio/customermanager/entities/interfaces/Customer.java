package com.macedocaio.customermanager.entities.interfaces;

import java.time.LocalDate;
import java.util.UUID;

public interface Customer {
    Long getId();
    UUID getResourceId();
    String getUsername();
    String getFirstname();
    String getLastname();
    LocalDate getBirthday();
    String getCpf();
    void setId(Long id);
    void setResourceId(UUID resourceId);
    void setUsername(String username);
    void setFirstname(String firstname);
    void setLastname(String lastname);
    void setBirthday(LocalDate birthday);
    void setCpf(String cpf);
}
