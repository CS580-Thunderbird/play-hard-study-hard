package edu.cpp.cs580.thunderbird.tools;

import java.time.LocalTime;

import edu.cpp.cs580.thunderbird.data.TimeObj;

public class TimeConvertor {

	public static LocalTime getLocalTime(String time){	

		LocalTime localTime; LocalTime.of(0, 0);
		if(time.contains("AM")){
			localTime = LocalTime.of(Integer.parseInt(time.substring(0, time.indexOf(":"))), 
									Integer.parseInt(time.substring(time.indexOf(":")+1, time.indexOf(":")+3)));
			System.out.println(localTime);
		}
		else{
			
			if((Integer.parseInt(time.substring(0, time.indexOf(":")))==12)){
				localTime = LocalTime.of( (Integer.parseInt(time.substring(0, time.indexOf(":")))), 
						Integer.parseInt(time.substring(time.indexOf(":")+1, time.indexOf(":")+3)));
			}
			else{
			localTime = LocalTime.of( (Integer.parseInt(time.substring(0, time.indexOf(":"))) + 12), 
						Integer.parseInt(time.substring(time.indexOf(":")+1, time.indexOf(":")+3)));
			}
			System.out.println(localTime);
		}
		
		localTime = LocalTime.of(0, 0);
		
		return localTime;
	}
	
}
