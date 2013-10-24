package com.miguelpoyatosmora.repository;


import com.miguelpoyatosmora.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface EventRepository extends MongoRepository<Event, String>, EventRepositoryCustom {
//    @Query("{ 'name':{$regex:?0}, time': {$gt:?1,$lt:?2}}")
//    List<Event> findByNameAndDatefromAndDateto(String name, Long dateFrom, Long dateTo);
}
