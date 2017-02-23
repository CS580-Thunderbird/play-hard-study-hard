package edu.cpp.cs580.thunderbird.data;

import java.util.ArrayList;
import java.util.List;

public class UserSetting {
	
	private String userID;
	private List<String> eventPreference = new ArrayList<String>();
	private List<EventObject> attendingEvents = new ArrayList<EventObject>();
	private List<CppClassSchedule> classLists = new ArrayList<CppClassSchedule>();
	
	public void setUserID(String userID){
		this.userID = userID;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public void setEventPreference(List<String> list){
		eventPreference = list;
	}
	
	public List<String> getEventPreference(){
		return eventPreference;
	}
	
	public void addAttendingEvents(EventObject event){
		attendingEvents.add(event);
	}
	
	public void removeAttendingEvents(EventObject event){
		attendingEvents.remove(event);
	}
	
	public List<EventObject> getAttendingEvents(){
		return attendingEvents;
	}
	
	public void addClassList(CppClassSchedule newClass){
		classLists.add(newClass);
	}
	
	public void removeClassList(CppClassSchedule classObj){
		classLists.remove(classObj);
	}
	
	public List<CppClassSchedule> getClasses(){
		return classLists;
	}
	
	
}
