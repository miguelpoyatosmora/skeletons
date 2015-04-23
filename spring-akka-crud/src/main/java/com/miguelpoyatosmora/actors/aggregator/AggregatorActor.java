package com.miguelpoyatosmora.actors.aggregator;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.miguelpoyatosmora.actors.messages.Message.Entry;
import com.miguelpoyatosmora.actors.messages.Message.Evict;
import com.miguelpoyatosmora.actors.messages.Message.Get;

import java.util.HashMap;
import java.util.Map;

public class AggregatorActor extends UntypedActor {
    Map<String, String> cache = new HashMap<>();

    public static final String NOT_FOUND = "NOT_FOUND";

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public AggregatorActor() {
        super();
        log.info("Instantiated AggregatorActor");
    }

    public void onReceive(Object msg) {
        log.info("Received " + msg);
        if (msg instanceof Entry) {
            Entry entry = (Entry) msg;
            answer(cache.put(entry.getKey(), entry.getValue()));
        } else if (msg instanceof Get) {
            Get get = (Get) msg;
            answer(cache.get(get.getKey()));
        } else if (msg instanceof Evict) {
            Evict evict = (Evict) msg;
            answer(cache.remove(evict.getKey()));
        } else {
            unhandled(msg);
        }
    }

    private void answer(String value) {
        getSender().tell(value == null ? NOT_FOUND : value,
                getContext().self());
    }
}

