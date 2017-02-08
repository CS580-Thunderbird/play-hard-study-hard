package edu.cpp.cs580.thunderbird.data.provider;

import edu.cpp.cs580.thunderbird.data.EventObject;

public interface EventManager {
	public void addNewEvent(EventObject event);
	public void listEvents();
}
