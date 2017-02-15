package edu.cpp.cs580.thunderbird.data.provider;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cpp.cs580.thunderbird.data.EventObject;
import edu.cpp.cs580.thunderbird.data.EventRepo;
import edu.cpp.cs580.thunderbird.tools.GetInternationalStudentEvents;


public class InternationalStudentEventManager implements EventManager{
	@Autowired EventRepo intRepo;
	GetInternationalStudentEvents getIntEvent;

	public void addNewEvent(EventObject event) {
		intRepo.save(event);
		
	}

	@Override
	public void listEvents() {
		 try {
			getIntEvent = new GetInternationalStudentEvents();
			getIntEvent.parseEvents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
