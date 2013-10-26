package com.miguelpoyatosmora.repository;


import com.miguelpoyatosmora.model.domain.Event;
import org.joda.time.DateTime;

import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findByOptionalNameAndDateRange(String name, DateTime datefrom, DateTime dateto);

    void incrementLikes(String id);
}
