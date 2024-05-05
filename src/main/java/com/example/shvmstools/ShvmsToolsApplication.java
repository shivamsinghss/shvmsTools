package com.example.shvmstools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.shvmstools", "com.example.shvmstools.config"})
public class ShvmsToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShvmsToolsApplication.class, args);
	}

}
