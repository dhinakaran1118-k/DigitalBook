package com.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyAPP {
	public static void main(String[] args) {
		SpringApplication.run(MyAPP.class, args);
		System.out.println("Application is running");
	}

}
