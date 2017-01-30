package edu.cpp.cs580.thunderbird.data;

import java.util.HashMap;
import java.util.Map;

public class CppClass {
	private String code;
	private TimeObj timeObj;
	private String building;
	private String room;
	private String description;
	private String instructor;
	
	
	public CppClass(String code, TimeObj timeObj, String building, String room, String description, String instructor){
		this.code = code;
		this.timeObj = timeObj;
		this.building = building;
		this.room = room;
		this.description = description;
		this.instructor = instructor;
		
	}
	
	public String getCode(){
		return code;
	}
	
	public TimeObj getTime(){
		return timeObj;
	}
	
	public String getBuilding(){
		return building;
	}
	
	public String getRoom(){
		return room;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getInstructor(){
		return instructor;
	}
}
