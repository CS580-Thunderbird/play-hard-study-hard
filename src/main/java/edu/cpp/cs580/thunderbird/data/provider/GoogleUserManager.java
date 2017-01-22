package edu.cpp.cs580.thunderbird.data.provider;

import edu.cpp.cs580.thunderbird.data.GoogleUser;

/**
 * Example from edu.cpp.cs580.data.provider
 * @author nchantarutai
 *
 */
public class GoogleUserManager implements UserManager{

	//Temporary. will come up will better method.
	private String jsonString;

	@Override
	public GoogleUser getUserId(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logOut(String userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addClass(String userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWorkTime(String userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEventPreference(String userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(String json) {
		this.jsonString = json;
		System.out.println("Test --- " + jsonString);
		
	}
	
}
