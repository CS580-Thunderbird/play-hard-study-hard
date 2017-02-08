package edu.cpp.cs580.thunderbird.data.provider;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.cpp.cs580.thunderbird.data.GoogleUser;
import edu.cpp.cs580.thunderbird.data.UserRepository;

/**
 * Example from edu.cpp.cs580.data.provider
 * @author nchantarutai
 *
 */
public class GoogleUserManager implements UserManager{

	@Autowired private UserRepository googleRepo; //database, may need to modify later
	@Autowired UserPreferenceSettingManager userPreference;
	
	private GoogleUser user;
	private String jsonUser;

	public GoogleUserManager(){
		
	}
	
	public GoogleUserManager(UserPreferenceSettingManager userPreference){
		this.userPreference = userPreference;
	}
	
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
		System.out.println("Test -- Update User");

		this.jsonUser = json;
		byte[] jsonData =json.getBytes();
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = objMapper.readTree(jsonData);
			
			
			try{
			if(rootNode.path("id").asText().equals(user.getId())) return;
			}catch(NullPointerException e){
				System.out.println("There are no userId in ther records");
			}
				
			user = new GoogleUser(rootNode.path("id").asText(), 
								rootNode.path("email").asText(),
								rootNode.path("name").asText(),
								rootNode.path("picture").asText());
			
			if(googleRepo.getUserByEmail(user.getEmail()) == null)
				addNewUser(user);

			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * First time that user use our webapp
	 */

	@Override
	public void addNewUser(GoogleUser user) {	
		googleRepo.save(user);		
		
		if(!userPreference.checkIfUserExist(user.getId())){
			userPreference.saveNewUser(user.getId());
		}else{
			System.out.println("User exist in repo");
		}
	}

	/**
	 * Made as string to be printed convenient
	 */
	@Override
	public String readAllUsers(){
		System.out.println("Read All Users ");
		StringBuilder string = new StringBuilder();
		
		for(GoogleUser userGoogle: googleRepo.findAll()){
			string.append(userGoogle.getEmail());
		}
		
		return string.toString();
	}
	
	/**
	 * Temorary Usage, will design it better
	 */
	
	@Override
	public String getJSONUser(){
		System.out.println(jsonUser);
		return jsonUser;
		
	}
	
	@Override
	public void logOut(){
		user = null;
		jsonUser = null;
	}
	
}
