package edu.cpp.cs580.thunderbird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableOAuth2Sso
public class MyApp {

	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
