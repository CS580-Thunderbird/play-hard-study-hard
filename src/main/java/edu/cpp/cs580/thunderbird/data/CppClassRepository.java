package edu.cpp.cs580.thunderbird.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CppClassRepository extends MongoRepository<CppClassSchedule, String>{
	
	public CppClassSchedule findClassByCode(String code);
	public List <CppClassSchedule> getClassByCode(String code);
}
