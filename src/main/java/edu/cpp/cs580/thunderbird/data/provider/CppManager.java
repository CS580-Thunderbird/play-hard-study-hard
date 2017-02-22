package edu.cpp.cs580.thunderbird.data.provider;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import edu.cpp.cs580.thunderbird.data.CppClassRepo;
import edu.cpp.cs580.thunderbird.data.CppClassSchedule;
import edu.cpp.cs580.thunderbird.utils.ResourceResolver;

public class CppManager {
	@Autowired CppClassRepo cppRepo;
	
	public void saveNewClass(CppClassSchedule newClass){
		cppRepo.save(newClass);
	}
	
	public void listClasses(){
		for(CppClassSchedule cpp : cppRepo.findAll()){
			System.out.println(cpp.getCode());
			System.out.println(cpp.getDescription());	
			
		}
	}
	
	public String listJSonClasses() throws IOException{
		
		File cppClassesJSON = ResourceResolver.getClassJson();
		
		
		StringWriter json = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(json);
		jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
		
		boolean first = true;
		for(CppClassSchedule cpp: cppRepo.findAll()){
			
			if(!first) jsonGenerator.writeRaw(",");
			else first = false;
			
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("code", cpp.getCode());
			jsonGenerator.writeStringField("description", cpp.getDescription());
			jsonGenerator.writeStringField("start_date", cpp.getClassTime().getStartDate().toString());
			jsonGenerator.writeStringField("end_date", cpp.getClassTime().getEndDate().toString());
			if(cpp.getClassTime().getStartTime() != null) jsonGenerator.writeStringField("start_time", cpp.getClassTime().getStartTime().toString());
			if(cpp.getClassTime().getEndTime() != null) jsonGenerator.writeStringField("end_time", cpp.getClassTime().getEndTime().toString());
			
			jsonGenerator.writeRaw("," + cpp.getClassTime().getJSonRepeatDay());
			jsonGenerator.writeEndObject();
			
		}
	
		
		jsonGenerator.flush();
		jsonGenerator.close();
		
		PrintWriter out = new PrintWriter(cppClassesJSON);
		out.print("[" + json.toString() +"]");
		
		return json.toString();
	}
	
}
