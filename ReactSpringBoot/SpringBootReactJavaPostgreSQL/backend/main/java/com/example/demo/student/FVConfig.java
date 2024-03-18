package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class FVConfig {

    @Bean
    CommandLineRunner commandLineRunner(FVRepository repository){
        return args -> {
            Faktura fv1 = new Faktura(
                    "Maluta",
                    "Mlekovita",
                    "FV/27/04/2023",
                    LocalDate.now(),
                    "Nowy Dwór Gdański",
                    LocalDate.now(),
                    LocalDate.now(),
                    "testowaUsluga",
                    3,
                    "szt",
                    100.0,
                    (byte) 23,
                    23,
                    123,
                    true,
                    "przelew"
            );

            Faktura fv2 = new Faktura(
                    "Gnub sp.z.o.o",
                    "Kajtek ",
                    "FV/30/11/2012",
                    LocalDate.now(),
                    "Gdańsk",
                    LocalDate.now(),
                    LocalDate.now(),
                    "testowaUsluga",
                    3,
                    "szt",
                    1000.0,
                    (byte) 23,
                    230,
                    1230.0,
                    true,
                    "Gotówka"
            );

            repository.saveAll(
                    List.of(fv1,fv2)
            );
        };
    }
}
