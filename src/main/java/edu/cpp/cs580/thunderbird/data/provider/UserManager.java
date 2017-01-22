package edu.cpp.cs580.thunderbird.data.provider;

import edu.cpp.cs580.thunderbird.data.GoogleUser;

/**
 * Example from edu.cpp.cs580.data.provider
 * @author nchantarutai
 *
 */
public interface UserManager {
	
	public GoogleUser getUserId(String userID);
	public void logOut(String userID);
	public void addClass(String userID);
	public void addWorkTime(String userID);
	public void addEventPreference(String userID);
	public void updateUser(String json);
	
}
