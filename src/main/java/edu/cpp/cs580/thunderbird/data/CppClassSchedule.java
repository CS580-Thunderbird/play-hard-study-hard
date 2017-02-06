package edu.cpp.cs580.thunderbird.data;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Data model for class from cal poly pomona
 * @author nchantarutai
 *
 */
public class CppClassSchedule {

	private String code;
	private String description;
	private TimeObj classTime;
	private String instructor;
	private String location; 
	
	public CppClassSchedule(String code, String description, TimeObj classTime, String instructor, String location){
		this.code = code;
		this.description = description;
		this.classTime = classTime;
		this.instructor = instructor; 
		this.location = location;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setClassTime(TimeObj classTime){
		this.classTime = classTime;
	}
	
	public TimeObj getClassTime(){
		return classTime;
	}
	
	public void setInstructor(String instructor){
		this.instructor = instructor;
	}
	
	public String getInstructor(){
		return instructor;
	}
	
	public String getLocation(){
		return location;
	}

}
