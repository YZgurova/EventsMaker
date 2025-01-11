package com.EventsMaker.v1.bin.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.EventsMaker.v1.beans",
		"com.EventsMaker.v1.auth",
		"com.EventsMaker.v1.api",
		"com.EventsMaker.v1.payments"
})
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
