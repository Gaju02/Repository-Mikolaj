package com.example.demo;

import com.example.demo.model.Content;
import com.example.demo.model.Status;
import com.example.demo.model.Type;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(Repository repository){
		return args -> {
			System.out.println("Hello world!");
			Content c = new Content(1,
					"My first Blog Post",
					"My first Blog post",
					Status.IDEA,
					Type.ARTICLE,
					LocalDateTime.now(),
					null,
					"");
			repository.save(c);
		};
	}


}
