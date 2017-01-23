package edu.cpp.cs580.thunderbird.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<GoogleUser, String>{
	public List<GoogleUser> findByEmail(String email);

}
