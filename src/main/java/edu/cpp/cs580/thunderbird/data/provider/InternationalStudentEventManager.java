package edu.cpp.cs580.thunderbird.data.provider;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cpp.cs580.thunderbird.data.EventObject;
import edu.cpp.cs580.thunderbird.data.InternationalStudentEvent;
import edu.cpp.cs580.thunderbird.data.InternationalStudentEventRepo;


public class InternationalStudentEventManager{
	@Autowired InternationalStudentEventRepo intRepo;

	public void addNewEvent(InternationalStudentEvent event) {
		intRepo.save(event);
		
	}

}
