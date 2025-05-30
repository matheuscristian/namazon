package com.nozama.api_nozama;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiNozamaApplication {
	private static Connection conn = null;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApiNozamaApplication.class, args);

		String url = "jdbc:mysql://localhost:3306/Amazon";
		String username = "root";
		String password = null;

		conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connected to the database!");
	}

	public static Connection getConn() {
		return conn;
	}
}
