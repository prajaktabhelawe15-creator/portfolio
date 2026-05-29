package com.portfolio.portfolio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication- shortcut for 3 annotations:
//	@Configuration- this class can define Spring beans
//	@EnableAutoConfiguration- Spring Boot auto-configures based on your classpath
//	@CoponentScan- scans this package and all sub-packages for @Controller, @Service, @Repository, @Component

public class PortfolioBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(PortfolioBackendApplication.class, args);
		//SpringApplication.run() bootstraps the entire application:
		//1.Creates the Spring ApplicationContext (the IoC container)
		//2.Starts the embedded Tomcat server
		//3.Scans and registers all beans
		//4.Sets up th;e database connection pool

	}

}
