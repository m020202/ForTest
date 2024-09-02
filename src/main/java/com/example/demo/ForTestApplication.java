package com.example.demo;

import com.example.demo.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForTestApplication.class, args);
		Person p1 = Person.ofName("Alice");
		Person p2 = Person.ofAge(30);
		System.out.println(p1.getName());
		System.out.println(p2.getAge());
	}
}
