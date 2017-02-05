package edu.cpp.cs580.thunderbird.data.provider;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import edu.cpp.cs580.thunderbird.data.EventOrganizer;
import edu.cpp.cs580.thunderbird.data.EventOrganizerListRepo;

public class EventOrganizerManager {
	@Autowired EventOrganizerListRepo eventOrgRepo;
	
	public void addNewEventOrganizer(EventOrganizer newOrganize){
		eventOrgRepo.save(newOrganize);
	}
	
	public void deleteAll(){
		eventOrgRepo.deleteAll();
	}
	
	//Temporary Usages
	public String getHardCodeOrgList() throws IOException{
		StringWriter json = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(json);
		jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
	
		jsonGenerator.writeStartArray();
		
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("id", "1");
			jsonGenerator.writeStringField("name", "ASI");
			jsonGenerator.writeStringField("link", "www.xxx.com");		
			jsonGenerator.writeEndObject();
			
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("id", "2");
			jsonGenerator.writeStringField("name", "Fitness");
			jsonGenerator.writeStringField("link", "www.xxx.com");		
			jsonGenerator.writeEndObject();
			
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("id", "3");
			jsonGenerator.writeStringField("name", "CSS");
			jsonGenerator.writeStringField("link", "www.xxx.com");		
			jsonGenerator.writeEndObject();
			
		
		jsonGenerator.writeEndArray();
		
		jsonGenerator.flush();
		jsonGenerator.close();
		return json.toString();
	}
	
	public String getJSonListOfOrganizer() throws FileNotFoundException, IOException{
		StringWriter json = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(json);
		jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
	
		jsonGenerator.writeStartArray();
		for(EventOrganizer org: eventOrgRepo.findAll()){
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("id", org.getOrganizerName());
			jsonGenerator.writeStringField("name", org.getOrganizerName());
			jsonGenerator.writeStringField("link", org.getOrganizerLink());
			
			jsonGenerator.writeEndObject();
			
		}
		jsonGenerator.writeEndArray();
		
		jsonGenerator.flush();
		jsonGenerator.close();
		return json.toString();
	}
	
	public void getByName(String name){
		List<EventOrganizer> results = eventOrgRepo.findByOrganizerName(name);
		for(EventOrganizer org: results){
			System.out.println("Org: " + org.getOrganizerName());
			
		}
	}
	
	public List<EventOrganizer> ListAllOrganizer(){
		System.out.println("Size:  " + eventOrgRepo.count());
		ArrayList<EventOrganizer> result = new ArrayList<EventOrganizer>();
		for(EventOrganizer org: eventOrgRepo.findAll()){
			System.out.println("Org: " + org.getOrganizerName());
			result.add(org);
		}
		return result;
	}
	
}
