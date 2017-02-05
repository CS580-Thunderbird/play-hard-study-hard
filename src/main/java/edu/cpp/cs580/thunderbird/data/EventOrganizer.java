package edu.cpp.cs580.thunderbird.data;

public class EventOrganizer {

	private String id;
	private String organizerName;
	private String organizerLink;
	
	public EventOrganizer(String id, String organizerName, String organizerLink){
		this.id = id;
		this.organizerName = organizerName;
		this.organizerLink = organizerLink;
	}
	
	public String getID(){
		return id;
	}
	
	public String getOrganizerName(){
		return organizerName;
	}
	
	public String getOrganizerLink(){
		return organizerLink;
	}
	
}
