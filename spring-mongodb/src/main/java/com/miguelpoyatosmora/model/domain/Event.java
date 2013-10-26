package com.miguelpoyatosmora.model.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public final class Event {

    @Id
    final private String id;
    final private String name;
    final private DateTime time;
    final private long likes;

    public Event(String id, String name, DateTime time, long likes) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateTime getTime() {
        return time;
    }

    public Long getLikes() {
        return likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (likes != event.likes) return false;
        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (time != null ? !time.equals(event.time) : event.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (int) (likes ^ (likes >>> 32));
        return result;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", likes=" + likes +
                '}';
    }
}
