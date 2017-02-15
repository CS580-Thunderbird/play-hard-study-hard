package edu.cpp.cs580.thunderbird.data;

public interface EventObject {
	
	public void setTitle(String title);
	public void setDetail(String details);
	public void setTimeObj(TimeObj timeObj);
	public void setLocation(String location);
	public void setLink(String link);
	public String getTitle();
	public String getDetail();
	public TimeObj getTimeObj();
	public String getLocation();
	public String getLink();
	
}
