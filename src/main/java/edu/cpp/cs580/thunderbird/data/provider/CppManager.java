package edu.cpp.cs580.thunderbird.data.provider;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cpp.cs580.thunderbird.data.CppClass;
import edu.cpp.cs580.thunderbird.data.CppClassRepo;

public class CppManager {
	@Autowired CppClassRepo cppRepo;
	
	public void saveNewClass(CppClass newClass){
		cppRepo.save(newClass);
	}
	
	public void ListClasses(){
		for(CppClass cpp : cppRepo.findAll()){
			System.out.println(cpp.getCode());
			System.out.println(cpp.getDescription());		
		}
	}
	
}
