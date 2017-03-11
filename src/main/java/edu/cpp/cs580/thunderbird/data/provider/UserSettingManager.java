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
	
	public String getCppClasses(String userID) throws IOException{
		
		//DEBUG
		if(userID == null) userID = "104397633282085651478";
		UserSetting user = userSettingRepo.findOne(userID);
		List<CppClassSchedule> classList = user.getClasses();
		
		StringWriter json = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(json);
		
		
			
		boolean first = true;
		for(CppClassSchedule c: classList){
			
			if(!first) jsonGenerator.writeRaw(",");
			else first = false;
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("title", c.getCode());
			jsonGenerator.writeStringField("startsAt", c.getClassTime().getStartDate().toString()+"T00:00:00");
			jsonGenerator.writeStringField("endsAt", c.getClassTime().getEndDate().toString()+"T00:00:00");
			jsonGenerator.writeStringField("org", "Class");
			jsonGenerator.writeStringField("location", c.getLocation());
			jsonGenerator.writeStringField("orgColor", "classColor");
			jsonGenerator.writeStringField("details", c.getDescription());
			jsonGenerator.writeEndObject();
			
		}
		
		
		
		jsonGenerator.flush();
		jsonGenerator.close();
		/**
		 * Pattern
		 * {
			  "title": "CS580",
			  "startsAt": "2017-03-01T16:00:00",
			  "endsAt": "2017-03-01T18:00:00",
			  "org": "Class",
			  "location": "Building 8",
			  "orgColor": "classColor",
			  "details": "Advanced Software Engineer"
			}
		 */
		
	
		
		
				
		return "[" + json.toString() + "]";
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
