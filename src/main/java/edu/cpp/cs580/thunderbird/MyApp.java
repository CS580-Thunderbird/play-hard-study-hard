package edu.cpp.cs580.thunderbird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.cpp.cs580.thunderbird.data.provider.CppManager;
import edu.cpp.cs580.thunderbird.data.provider.EventManager;
import edu.cpp.cs580.thunderbird.data.provider.EventOrganizerManager;
import edu.cpp.cs580.thunderbird.data.provider.GoogleUserManager;
import edu.cpp.cs580.thunderbird.data.provider.InternationalStudentEventManager;
import edu.cpp.cs580.thunderbird.data.provider.UserManager;

@SpringBootApplication
public class MyApp {

	@Bean
	public UserManager userManager(){
		//What is the point of create interface ?
		UserManager userManager = new GoogleUserManager();
		return userManager;
	}
	
	
	@Bean
	public CppManager cppManager(){
		//Can i create class without interface ? Is it good implementation.
		CppManager cppManager = new CppManager();
		return cppManager;
	}
	
	@Bean 
	public EventOrganizerManager eventOrganizerManager(){
		EventOrganizerManager eventOrganizerManager = new EventOrganizerManager();
		return eventOrganizerManager;
	}
	
	@Bean
	public EventManager eventManager(){
		EventManager eventManager = new InternationalStudentEventManager();
		
		//If I want to create another instance for another class that implement the same interface ? 
		//So use design pattern to fix my problem ?
		return eventManager;
	}

	
	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
