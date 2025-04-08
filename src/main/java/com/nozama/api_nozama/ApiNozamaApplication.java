package com.nozama.api_nozama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiNozamaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNozamaApplication.class, args);

		String url = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "your_username";
        String password = "your_password";

		
	}

}
