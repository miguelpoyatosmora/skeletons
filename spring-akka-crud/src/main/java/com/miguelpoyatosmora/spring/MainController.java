package com.miguelpoyatosmora.spring;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import com.miguelpoyatosmora.actors.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import scala.concurrent.Future;

import javax.annotation.PostConstruct;

@RestController
public class MainController {

    Logger log = LoggerFactory.getLogger(MainController.class);

    @Value("${app.get.timeout:1000}")
    private long timeout;

    private ActorSelection router;

    @Autowired
    private ActorSystem as;

    @PostConstruct
    private void init() {
        router = as.actorSelection("akka.tcp://default@127.0.0.1:2552/user/coordinator/router");
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public DeferredResult<String> get(@PathVariable String key) {
        DeferredResult<String> result = new DeferredResult<>();

        Future<Object> ask = Patterns.ask(router, new Message.Get(key), timeout);
        ask.onSuccess(new OnSuccess<Object>() {
            @Override
            public final void onSuccess(Object value) {
                result.setResult((String) value);
                System.out.println(value);
            }

        }, as.dispatcher());

        ask.onFailure(new OnFailure() {
            @Override
            public void onFailure(Throwable failure) throws Throwable {
                System.out.println("" + failure);
                result.setErrorResult(failure);
            }

        }, as.dispatcher());

        return result;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.POST)
    public DeferredResult<String> post(@PathVariable String key,
                                       @RequestParam String value) {
        DeferredResult<String> result = new DeferredResult<>();

        Future<Object> ask = Patterns.ask(router, new Message.Entry(key, value), timeout);
        ask.onSuccess(new OnSuccess<Object>() {
            @Override
            public final void onSuccess(Object t) {
                String result1 = (String) t;
                result.setResult(result1);
                log.info(result1);
            }

        }, as.dispatcher());

        ask.onFailure(new OnFailure() {
            @Override
            public void onFailure(Throwable failure) throws Throwable {
                System.out.println("" + failure);
                result.setErrorResult(failure);
            }

        }, as.dispatcher());

        return result;
    }

}
