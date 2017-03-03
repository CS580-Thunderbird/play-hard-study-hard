package edu.cpp.cs580.thunderbird.data.provider;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import edu.cpp.cs580.thunderbird.data.CppClassRepo;
import edu.cpp.cs580.thunderbird.data.CppClassSchedule;
import edu.cpp.cs580.thunderbird.data.UserSetting;
import edu.cpp.cs580.thunderbird.data.UserSettingRepo;

public class UserSettingManager {
	@Autowired UserSettingRepo userSettingRepo;
	@Autowired CppClassRepo classRepo;
	
	Query query = new Query();
	
	public void saveNewUser(UserSetting newUser){
		userSettingRepo.save(newUser);
	}
	
	public boolean isNewUser(String userID){
		if(userSettingRepo.getUserByUserID(userID)!=null)
			return false;
		else return true;
	}

	public void setEventPreference(List<String> list, String userID){
		UserSetting user = userSettingRepo.findOne(userID);
		user.setEventPreference(list);
		userSettingRepo.save(user);
	}
	
	public void addClassToList(String newCode, String userID){
		UserSetting user = userSettingRepo.findOne(userID);
		user.addClassList(classRepo.findByclassNbr(newCode));
		userSettingRepo.save(user);
	}
	
	public String getJsonOfPreferenceSetting(String userID) throws IOException{
		StringWriter json = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(json);
		jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
	
		UserSetting user = userSettingRepo.getUserByUserID(userID);
		
		//jsonGenerator.writeArrayFieldStart("preferset");
		
		jsonGenerator.writeStartArray();
		for(String id : user.getEventPreference()){
			jsonGenerator.writeString(id);
		}
		
		jsonGenerator.writeEndArray();
		jsonGenerator.flush();
		jsonGenerator.close();
		
		return json.toString();
	}
	
}
