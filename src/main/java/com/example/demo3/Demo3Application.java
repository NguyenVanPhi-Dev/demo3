package com.example.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan("com.example.demo3")
public class Demo3Application {
	@Autowired
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

}
