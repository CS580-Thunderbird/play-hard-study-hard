package edu.cpp.cs580.thunderbird.data;

import java.time.DayOfWeek;
import java.util.Date;

/**
 * Time Model for Events and Class
 * @author nchantarutai
 *
 */
public class Time {
	
	
	Date startDate;
	Date endDate;
	
	Time startTime;
	Time endTime;
	
	/**
	 * May need to redesign
	 * {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday}
	 */
	boolean[] repeatDay = {false, false, false, false, false, false, false};	
	DayOfWeek[] dayOfWeeks = DayOfWeek.values();
	
	public void setStartDate(Date date){
		this.startDate = date;
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	public void setEndDate(Date date){
		this.endDate = date;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
	public void setStartTime(Time time){
		this.startTime = time; 
	}
	
	public Time getStartTime(){
		return startTime;
	}
	
	public void setEndTime(Time time){
		this.endTime = time;
	}
	
	public Time getEndTime(){
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
}
