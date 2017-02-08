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
import edu.cpp.cs580.thunderbird.data.provider.UserPreferenceSettingManager;

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
	
	@Bean 
	public EventOrganizerManager eventOrganizerManager(){
		EventOrganizerManager eventOrganizerManager = new EventOrganizerManager();
		return eventOrganizerManager;
	}
	
	@Bean
	public EventManagers eventManagers(){
		EventManagers eventManagers = new EventManagers();
		return eventManagers;
	}
	

	@Bean
	public UserPreferenceSettingManager userPreferenceManager(){
		UserPreferenceSettingManager userPreferenceManager = new UserPreferenceSettingManager();
		return userPreferenceManager;
	}

	
	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
