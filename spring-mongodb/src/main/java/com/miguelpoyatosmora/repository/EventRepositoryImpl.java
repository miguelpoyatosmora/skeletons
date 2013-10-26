package com.miguelpoyatosmora.repository;


import com.miguelpoyatosmora.model.domain.Event;
import com.miguelpoyatosmora.model.exception.EventNotFoundException;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class EventRepositoryImpl implements EventRepositoryCustom {


    @Resource
    private MongoOperations mongoOps;

    @Override
    public List<Event> findByOptionalNameAndDateRange(String name, DateTime datefrom, DateTime dateto) {

        List<Criteria> criterias = new ArrayList<Criteria>(3);

        if (datefrom != null) {
            criterias.add(where("time").gt(datefrom));
        }
        if (dateto != null) {
            criterias.add(where("time").lt(dateto));
        }
        if (name != null) {
            criterias.add(where("name").regex(name));
        }

        return mongoOps.find(query(joinCriterias(criterias)), Event.class);
    }

    @Override
    public void incrementLikes(String id) {

        Event modifiedEvent = mongoOps.findAndModify(query(where("id").is(id)), new Update().inc("likes", 1), Event.class);
        if (modifiedEvent == null) throw new EventNotFoundException(id);
    }

    private static Criteria joinCriterias(List<Criteria> criterias) {

        int size = criterias.size();
        if (size == 0) return new Criteria();

        Criteria[] criteriaArray = criterias.toArray(new Criteria[size]);
        return new Criteria().andOperator(criteriaArray);
    }

}
