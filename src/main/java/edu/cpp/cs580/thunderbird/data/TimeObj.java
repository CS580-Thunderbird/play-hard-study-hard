package edu.cpp.cs580.thunderbird.data;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Time Model for Events and Class
 * @author nchantarutai
 *
 */
public class TimeObj {

	LocalDate startDate;
	LocalDate endDate;
	
	LocalTime startTime;
	LocalTime endTime;
	
	/**
	 * May need to redesign
	 * {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday}
	 */
	boolean[] repeatDay = {false, false, false, false, false, false, false};	
	DayOfWeek[] dayOfWeeks = DayOfWeek.values();
	
	public void setStartDate(LocalDate date){
		this.startDate = date;
	}
	
	public LocalDate getStartDate(){
		return startDate;
	}
	
	public void setEndDate(LocalDate date){
		this.endDate = date;
	}
	
	public LocalDate getEndDate(){
		return endDate;
	}
	
	public void setStartTime(LocalTime time){
		this.startTime = time; 
	}
	
	public LocalTime getStartTime(){
		return startTime;
	}
	
	public void setEndTime(LocalTime time){
		this.endTime = time;
	}
	
	public LocalTime getEndTime(){
		return endTime;
	}
	
	public void setRepeatDay(String day){
		repeatDay[DayOfWeek.valueOf(day).getValue()] = true;
	}
	
	public void setRepeatDay(boolean sun, boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat){
		repeatDay[0] = sun;
		repeatDay[1] = mon;
		repeatDay[2] = tue;
		repeatDay[3] = wed;
		repeatDay[4] = thu;
		repeatDay[5] = fri;
		repeatDay[6] = sat;
	}
	
	public boolean[] getRepeatDay(){
		return repeatDay;
	}
	
	public boolean getRepeatDay(String day){
		return repeatDay[DayOfWeek.valueOf(day).getValue()];
	}
	
	public String getJSonRepeatDay(){
		String result = "\"repeatDay\":[";
		
		result += "\"Sunday\":" + repeatDay[0] + ",";
		result += "\"Monday\":" + repeatDay[1] + ",";
		result += "\"Tuesday\":" + repeatDay[2] + ",";
		result += "\"Wednesday\":" + repeatDay[3] + ",";
		result += "\"Thursday\":" + repeatDay[4] + ",";
		result += "\"Friday\":" + repeatDay[5] + ",";
		result += "\"Saturday\":" + repeatDay[6];
		
		result +="]";
		
		return result;
	}
}
