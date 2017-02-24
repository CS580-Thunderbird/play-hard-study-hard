package edu.cpp.cs580.thunderbird.data.provider;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cpp.cs580.thunderbird.data.UserSetting;
import edu.cpp.cs580.thunderbird.data.UserSettingRepo;

public class UserSettingManager {
	@Autowired UserSettingRepo userSettingRepo;
	
	public void saveNewUser(UserSetting newUser){
		userSettingRepo.save(newUser);
	}
	
	public boolean isNewUser(String userID){
		if(userSettingRepo.getUserByUserID(userID)!=null)
			return false;
		else return true;
	}

}
