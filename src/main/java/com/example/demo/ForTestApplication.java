package com.example.demo;

import com.example.demo.coffee.CoffeeMachine;
import com.example.demo.domain.Movie;
import com.example.demo.domain.Person;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForTestApplication.class, args);
	}
}
