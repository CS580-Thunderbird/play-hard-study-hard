package edu.cpp.cs580.thunderbird.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSettingRepo extends MongoRepository<UserSetting, String>{
	public UserSetting getUserByUserID(String userID);

}
