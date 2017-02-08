package edu.cpp.cs580.thunderbird.data;

import java.util.ArrayList;

public class UserPreferenceSetting {
		String userId;
		ArrayList<String> organizerPreferences = new ArrayList<String>();
		ArrayList<String> classCodes = new ArrayList<String>();
		
		public UserPreferenceSetting(String userId){
			this.userId = userId;
		}
		
		
}
