package edu.cpp.cs580.thunderbird.data;

/**
 * Data model for class from cal poly pomona
 * @author nchantarutai
 *
 */
public class CppClass {
	private String code;
	private String description;
	private Time classTime;
	private String instructor;
	
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
	
	public void setClassTime(Time classTime){
		this.classTime = classTime;
	}
	
	public Time getClassTime(){
		return classTime;
	}
	
	public void setInstructor(String instructor){
		this.instructor = instructor;
	}
	
	public String getInstructor(){
		return instructor;
	}
}
