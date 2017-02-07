package edu.cpp.cs580.thunderbird.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepo extends MongoRepository<EventObject, String>{

}
