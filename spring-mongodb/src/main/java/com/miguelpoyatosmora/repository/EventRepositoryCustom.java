package com.miguelpoyatosmora.repository;


import com.miguelpoyatosmora.domain.Event;

import java.util.Date;
import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findByOptionalNameAndDateRange(String name, Date datefrom, Date dateto);
}
