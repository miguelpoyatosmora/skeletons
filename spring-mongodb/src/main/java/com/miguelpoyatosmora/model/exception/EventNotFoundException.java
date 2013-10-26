package com.miguelpoyatosmora.model.exception;


public class EventNotFoundException extends RuntimeException {

    private String id;

    public EventNotFoundException(String id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "EventNotFoundException{" +
                "id='" + id + '\'' +
                '}';
    }
}
