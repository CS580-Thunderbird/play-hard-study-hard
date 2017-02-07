package edu.cpp.cs580.thunderbird.data.provider;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cpp.cs580.thunderbird.data.EventObject;
import edu.cpp.cs580.thunderbird.data.EventRepo;


public class InternationalStudentEventManager implements EventManager{
	@Autowired EventRepo intRepo;

	public void addNewEvent(EventObject event) {
		intRepo.save(event);
		
	}

}
