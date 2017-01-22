package edu.cpp.cs580.thunderbird.data;

public class GoogleUser {
	private String id;
	private String email;
	private String name;
	private String pictureLink;
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPictureLink(String pictureLink){
		this.pictureLink = pictureLink;
	}
	
	public String getPictureLink(){
		return pictureLink;
	}
}
