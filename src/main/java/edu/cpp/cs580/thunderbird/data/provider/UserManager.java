package edu.cpp.cs580.thunderbird.data.provider;

import edu.cpp.cs580.thunderbird.data.GoogleUser;

/**
 * Need to design this interface or change to be abstract later
 * @author nchantarutai
 *
 */
public interface UserManager {
	
	public String getUserId();
	public String getUserName();
	public String getUserEmail();
	public void logOut(String userID);
	public void addClass(String userID);
	public void addWorkTime(String userID);
	public void addEventPreference(String userID);
	public void updateUser(String json);
	public void addNewUser(GoogleUser user); //If this is first time with our web service
	public String readAllUsers();
	public String getJSONUser();
	public void logOut();
	
	
}
