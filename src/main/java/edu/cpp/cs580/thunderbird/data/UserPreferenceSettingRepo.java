package edu.cpp.cs580.thunderbird.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPreferenceSettingRepo extends MongoRepository<UserPreferenceSetting, String>{
		public UserPreferenceSetting getUserByUserId(String userId);
}
