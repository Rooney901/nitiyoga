package com.rooney.nitiyoga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rooney.niti.*")
public class NitiyogaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NitiyogaApplication.class, args);
	}

}
