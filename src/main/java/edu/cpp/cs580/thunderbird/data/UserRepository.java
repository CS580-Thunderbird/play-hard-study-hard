package edu.cpp.cs580.thunderbird.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<GoogleUser, String>{
	
	public GoogleUser findById(String id);
	

}
