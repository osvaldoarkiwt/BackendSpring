package br.com.iwt.pizzaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzariaDeliveryApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/pizzaria");
		SpringApplication.run(PizzariaDeliveryApplication.class, args);
	}
}