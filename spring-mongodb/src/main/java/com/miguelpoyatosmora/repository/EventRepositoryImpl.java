package com.miguelpoyatosmora.repository;


import com.miguelpoyatosmora.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

public class EventRepositoryImpl implements EventRepositoryCustom {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MongoOperations mongoOps;

    @Override
    public List<Event> findByOptionalNameAndDateRange(String name, Date datefrom, Date dateto) {
        logger.debug("findByOptionalNameAndDateRange controller");
        List<Criteria> criterias = new ArrayList<Criteria>();

        if (datefrom != null) {
            criterias.add(Criteria.where("time").gt(datefrom));
        }
        if (dateto != null) {
            criterias.add(Criteria.where("time").lt(dateto));
        }
        if (name != null) {
            criterias.add(Criteria.where("name").regex(name));
        }

        return mongoOps.find(query(joinCriterias(criterias)), Event.class);
    }

    private static Criteria joinCriterias(List<Criteria> criterias) {

        int size = criterias.size();
        if (size == 0) return new Criteria();

        Criteria[] criteriaArray = criterias.toArray(new Criteria[size]);
        return new Criteria().andOperator(criteriaArray);
    }

}
