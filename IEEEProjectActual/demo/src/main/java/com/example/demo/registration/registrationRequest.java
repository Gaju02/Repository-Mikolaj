package com.example.demo.registration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class registrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public registrationRequest(String firstName,
                               String lastName,
                               String email,
                               String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
