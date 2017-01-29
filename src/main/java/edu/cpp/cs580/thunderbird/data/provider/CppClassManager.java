package edu.cpp.cs580.thunderbird.data.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import edu.cpp.cs580.thunderbird.data.CppClassRepository;
import edu.cpp.cs580.thunderbird.data.CppClassSchedule;

@Service
@Configurable
public class CppClassManager {
	
	@Autowired private CppClassRepository cppClassRepo; // Not work need to recheck

	public void addNewClassToList(CppClassSchedule newClass){
		cppClassRepo.save(newClass);
	}
	
	public void test(){
		
	}
	
}
