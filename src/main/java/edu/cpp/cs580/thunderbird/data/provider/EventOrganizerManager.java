package edu.cpp.cs580.thunderbird.data.provider;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cpp.cs580.thunderbird.data.EventOrganizer;
import edu.cpp.cs580.thunderbird.data.EventOrganizerListRepo;

public class EventOrganizerManager {
	@Autowired EventOrganizerListRepo eventOrgRepo;
	
	public void addNewEventOrganizer(EventOrganizer newOrganize){
		eventOrgRepo.save(newOrganize);
	}
	
	public String getJSonListOfOrganizer(){
		String json = "";
		
		
		return "";
	}
	
}
