package edu.cpp.cs580.thunderbird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import edu.cpp.cs580.thunderbird.data.provider.GoogleUserManager;
import edu.cpp.cs580.thunderbird.data.provider.UserManager;

@SpringBootApplication
public class MyApp {

	@Bean
	public UserManager userManager(){
		UserManager userManager = new GoogleUserManager();
		return userManager;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
