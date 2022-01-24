package org.csystem.app.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class App {
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
