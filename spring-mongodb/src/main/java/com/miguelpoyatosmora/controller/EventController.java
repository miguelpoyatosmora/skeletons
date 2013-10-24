package com.miguelpoyatosmora.controller;

import com.miguelpoyatosmora.controller.dto.CreateEventDTO;
import com.miguelpoyatosmora.controller.dto.RequestErrorDTO;
import com.miguelpoyatosmora.domain.Event;
import com.miguelpoyatosmora.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    EventRepository eventRepository;

    @RequestMapping(value = "/event/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable String id) {
        logger.debug("getEvent " + id);

        return eventRepository.findOne(id);
    }

    @RequestMapping(value = "/event/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createEvent(@RequestBody CreateEventDTO createEventDTO) {
        logger.debug("createEvent " + createEventDTO);

        Event event = new Event(null, createEventDTO.getName(), new Date(createEventDTO.getTime()));
        return eventRepository.save(event).getId();
    }

    @RequestMapping(value = "/events")
    public
    @ResponseBody
    List<Event> listEvents(
            @RequestParam(value = "dateFrom", required = false) Long dateFrom,
            @RequestParam(value = "dateTo", required = false) Long dateTo,
            @RequestParam(value = "name", required = false) String name) {
        logger.debug("listEvents");
        return eventRepository.findByOptionalNameAndDateRange(name, longToDate(dateFrom), longToDate(dateTo));
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public RequestErrorDTO exceptionHandler(Exception e) {
//        logger.debug(e.getClass() + " " + e.getMessage());
//
//        return new RequestErrorDTO(e.getMessage());
//    }

    private static Date longToDate(Long date) {
        return date == null ? null : new Date(date);
    }
}
