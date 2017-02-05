package edu.cpp.cs580.thunderbird.data.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cpp.cs580.thunderbird.data.EventOrganizer;
import edu.cpp.cs580.thunderbird.data.EventOrganizerListRepo;

public class EventOrganizerManager {
	@Autowired EventOrganizerListRepo eventOrgRepo;
	
	public void addNewEventOrganizer(EventOrganizer newOrganize){
		eventOrgRepo.save(newOrganize);
	}
	
	public void deleteAll(){
		eventOrgRepo.deleteAll();
	}
	
	public String getJSonListOfOrganizer(){
		String json = "";
		
		
		return "";
	}
	
	public List<EventOrganizer> ListAllOrganizer(){
		System.out.println("Size:  " + eventOrgRepo.count());
		ArrayList<EventOrganizer> result = new ArrayList<EventOrganizer>();
		for(EventOrganizer org: eventOrgRepo.findAll()){
			System.out.println("Org: " + org.getOrganizerName());
			result.add(org);
		}
		return result;
	}
	
}
