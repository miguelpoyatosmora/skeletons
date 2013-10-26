package com.miguelpoyatosmora.controller;

import com.miguelpoyatosmora.model.domain.Event;
import com.miguelpoyatosmora.model.dto.CreateEventDTO;
import com.miguelpoyatosmora.model.dto.RequestErrorDTO;
import com.miguelpoyatosmora.model.exception.EventNotFoundException;
import com.miguelpoyatosmora.service.EventService;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {

    @Resource
    private EventService eventService;

    @RequestMapping(value = "/event/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String create(@Valid @RequestBody CreateEventDTO createEventDTO) {

        return eventService.create(createEventDTO.getName(), createEventDTO.getTime());
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Event get(@PathVariable String id) {

        return eventService.get(id);
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void like(@PathVariable String id) {

        eventService.like(id);
    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {

        eventService.delete(id);
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Event> list(
            @RequestParam(value = "dateFrom", required = false) Long dateFrom,
            @RequestParam(value = "dateTo", required = false) Long dateTo,
            @RequestParam(value = "name", required = false) String name) {

        return eventService.findByOptionalNameAndDateRange(name, longToDateTime(dateFrom), longToDateTime(dateTo));
    }

    private static DateTime longToDateTime(Long date) {
        return date == null ? null : new DateTime(date);
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public RequestErrorDTO exceptionHandler(Exception e) {

        return new RequestErrorDTO(e.getMessage());
    }
}
