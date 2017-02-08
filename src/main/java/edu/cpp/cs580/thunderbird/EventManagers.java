package edu.cpp.cs580.thunderbird;

import edu.cpp.cs580.thunderbird.data.provider.ASIEventManager;
import edu.cpp.cs580.thunderbird.data.provider.EventManager;
import edu.cpp.cs580.thunderbird.data.provider.InternationalStudentEventManager;

public class EventManagers {
	public EventManager internationalStudentEvent;
	public EventManager asiEventManager;
	
	public EventManagers(){
		internationalStudentEvent = new InternationalStudentEventManager();
		asiEventManager = new ASIEventManager();
	}
	
}
