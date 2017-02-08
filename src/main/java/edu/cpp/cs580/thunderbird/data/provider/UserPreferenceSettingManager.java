package edu.cpp.cs580.thunderbird.data.provider;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cpp.cs580.thunderbird.data.UserPreferenceSetting;
import edu.cpp.cs580.thunderbird.data.UserPreferenceSettingRepo;

public class UserPreferenceSettingManager {
	@Autowired private UserPreferenceSettingRepo userSettingRepo;
	
	public void saveNewUser(String userId){
		userSettingRepo.save(new UserPreferenceSetting(userId));
		System.out.println("Done save new user " + userId);
	}
	
	public boolean checkIfUserExist(String userId){
		if(userSettingRepo.getUserByUserId(userId)!=null) return true;
		else return false;
	}
	
}
