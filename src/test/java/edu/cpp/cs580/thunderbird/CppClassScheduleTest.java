package edu.cpp.cs580.thunderbird;

import edu.cpp.cs580.thunderbird.data.CppClassSchedule;
import edu.cpp.cs580.thunderbird.data.TimeObj;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Before;

public class CppClassScheduleTest {

	private CppClassSchedule schedule;
	
	@Before
	public void setup() {
		
		String classCode = "CS580";
		String classDesc = "Advanced Software Engineering";
		TimeObj classTime = new TimeObj();
		String instructor = "Yu Sun";
		String location = "Building 9";
		String classNbr = "11734";
		
		schedule = new CppClassSchedule(classCode, classDesc, classTime, instructor, location, classNbr);
	}
	
	@Test
	public void testGetCode() {
		String classCode = schedule.getCode();
		assertEquals(classCode, "CS580");
	}
	
	@Test
	public void testSetCode() {
		schedule.setCode("CS664");
		String classCode = schedule.getCode();
		assertEquals(classCode, "CS664");
	}
}
