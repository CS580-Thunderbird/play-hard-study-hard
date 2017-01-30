package edu.cpp.cs580.thunderbird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.cpp.cs580.thunderbird.data.provider.CppManager;
import edu.cpp.cs580.thunderbird.data.provider.GoogleUserManager;
import edu.cpp.cs580.thunderbird.data.provider.UserManager;

@SpringBootApplication
public class MyApp {

	@Bean
	public UserManager userManager(){
		UserManager userManager = new GoogleUserManager();
		return userManager;
	}
	
	
	@Bean
	public CppManager cppManager(){
		CppManager cppManager = new CppManager();
		return cppManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
