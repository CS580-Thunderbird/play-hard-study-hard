package edu.cpp.cs580.thunderbird;

import edu.cpp.cs580.thunderbird.data.EventOrganizer;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;

public class EventOrganizerTest {
    
	private EventOrganizer organizer;
	
	@Before
	public void setup() {
	    
		String organizerID = "CS580";
		String organizerName = "Yu Sun";
		String organizerLink = "http://yusun.io";
		
		organizer = new EventOrganizer(organizerID, organizerName, organizerLink);
	}
	
	@Test
	public void testGetId() {
	    String classID = organizer.getID(); 
	    
	    Assert.assertEquals(classID, "CS580");
	}
	
	@Test
    public void testGetOrganizerLink() {
        String orgLink = organizer.getOrganizerLink(); 
        
        Assert.assertEquals(orgLink, "http://yusun.io");
    }
	
	@Test
    public void testGetOrganizerName() {
        String orgName = organizer.getOrganizerName(); 
        
        Assert.assertEquals(orgName, "Yu Sun");
    }
	
}
