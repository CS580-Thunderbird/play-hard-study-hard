package edu.cpp.cs580.thunderbird.data;

public interface EventObject {
	
	public void setTitle(String title);
	public void setDetail(String details);
	public void setTimeObj(TimeObj timeObj);
	public String getTitle();
	public String getDetail();
	public TimeObj getTimeObj();
}
