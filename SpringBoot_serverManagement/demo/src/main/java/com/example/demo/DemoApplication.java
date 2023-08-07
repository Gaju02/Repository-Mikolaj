package com.example.demo;

import com.example.demo.Repo.ServerRepo;
import com.example.demo.enumeration.Status;
import com.example.demo.model.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null,
					"192.168.1.160",
					"Ubuntu Linux",
					"16GB",
					"Personal PC", "https://localhost:8080/server/image/server1.png",
					Status.SERVER_UP));
		};
	}
}

