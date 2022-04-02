package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.NOVEMBER;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student Miki =new Student(
                    1L,
                    "Miki",
                    "mikolaj.gajewski.2@gmail.com",
                    LocalDate.of(2002, NOVEMBER,20),
                    18

            );
            Student Alex =new Student(
                    2L,
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, NOVEMBER,20),
                    21


            );

            repository.saveAll(
                    List.of(Miki,Alex)
            );
        };
    }

}
