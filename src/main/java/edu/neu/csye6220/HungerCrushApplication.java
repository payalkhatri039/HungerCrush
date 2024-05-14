package edu.neu.csye6220;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication replaces @Configuration, @EnableAutoConfiguration and @ComponentScan
@SpringBootApplication
public class HungerCrushApplication {

	public static void main(String[] args) {
		SpringApplication.run(HungerCrushApplication.class, args);
	}

}
