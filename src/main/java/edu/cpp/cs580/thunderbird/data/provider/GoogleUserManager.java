package edu.cpp.cs580.thunderbird.data.provider;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.cpp.cs580.thunderbird.data.GoogleUser;

/**
 * Example from edu.cpp.cs580.data.provider
 * @author nchantarutai
 *
 */
public class GoogleUserManager implements UserManager{

	private GoogleUser user;

	@Override
	public String getUserId() {
		return user.getId();
	}
	
	public String getUserName(){
		return user.getName();
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

	/**
	 * After user authorized the email
	 */
	@Override
	public void updateUser(String json) {
		byte[] jsonData =json.getBytes();
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = objMapper.readTree(jsonData);
			//Need to come up with better method to check if we already authorize the user
			user = new GoogleUser(rootNode.path("id").asText(), 
								rootNode.path("email").asText(),
								rootNode.path("name").asText(),
								rootNode.path("picture").asText());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addNewUser(GoogleUser user) {
		// TODO Auto-generated method stub
		
	}
	
}
