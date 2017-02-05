package edu.cpp.cs580.thunderbird.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EventOrganizerListRepo extends MongoRepository<EventOrganizer, String>{

	List<EventOrganizer> findByOrganizerName(String organizerName);
	
}
