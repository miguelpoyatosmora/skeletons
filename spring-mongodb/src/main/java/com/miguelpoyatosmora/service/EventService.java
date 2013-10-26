package com.miguelpoyatosmora.service;

import com.miguelpoyatosmora.model.domain.Event;
import com.miguelpoyatosmora.repository.EventRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EventService {

    @Resource
    private EventRepository eventRepository;

    public void like(String id) {

        eventRepository.incrementLikes(id);
    }

    public Event get(String id){

        return eventRepository.findOne(id);
    }

    public String create(String name, Long time) {

        return eventRepository.save(new Event(null, name, new DateTime(time), 0L)).getId();
    }

    public List<Event> findByOptionalNameAndDateRange(String name, DateTime dateFrom, DateTime dateTo) {

        return eventRepository.findByOptionalNameAndDateRange(name, dateFrom, dateTo);
    }

    public void delete(String id) {

        eventRepository.delete(id);
    }


}
