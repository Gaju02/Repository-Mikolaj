package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record Content(

        Integer id,
        @NotBlank
        String title,
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        Local dateUpdated,
        String url
) {
}
