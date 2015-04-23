package com.miguelpoyatosmora.actors.coordinator;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ConsistentHashingPool;
import com.miguelpoyatosmora.actors.aggregator.AggregatorActor;
import com.miguelpoyatosmora.actors.messages.HasKey;


public class CoordinatorActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef router;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        createRouter();
        log.info("CoordinatorActor starting");
    }

    public void onReceive(Object msg) {
        log.info("CoordinatorActor received " + msg);
        if (msg instanceof Terminated) {
            log.warning("CoordinatorActor child dead " + ((Terminated) msg).actor().path());
            createRouter();
        } else {
            log.warning("CoordinatorActor unhandled " + msg);
            unhandled(msg);
        }
    }

    private void createRouter() {
        router = getContext().actorOf(new ConsistentHashingPool(10).withHashMapper(message -> {
            if (message instanceof HasKey) {
                log.warning("Router Mmpper could handle message {}", message);
                return ((HasKey) message).getKey();
            } else {
                log.warning("Router Mmpper could not handle message {}", message);
                return null;
            }
        }).props(Props.create(AggregatorActor.class)), "router");

        getContext().watch(router);
        log.info("CoordinatorActor created " + router.path().toString());
    }

}
