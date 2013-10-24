package com.miguelpoyatosmora.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguelpoyatosmora.domain.Event;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Miguel
 * Date: 24/10/13
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class EventMessageConverter extends AbstractHttpMessageConverter<Event> {
    private ObjectMapper mapper = new ObjectMapper();

    public EventMessageConverter() {
    }

    public EventMessageConverter(MediaType supportedMediaType) {
        super(supportedMediaType);
    }

    public EventMessageConverter(MediaType... supportedMediaTypes) {
        super(supportedMediaTypes);
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return Event.class.equals(aClass);
    }

    @Override
    protected Event readInternal(Class<? extends Event> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return mapper.readValue(httpInputMessage.getBody(), Event.class);
    }

    @Override
    protected void writeInternal(Event event, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        httpOutputMessage.getBody().write(mapper.writeValueAsBytes(event));
    }
}
