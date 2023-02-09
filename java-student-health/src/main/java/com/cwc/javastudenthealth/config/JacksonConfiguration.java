package com.cwc.javastudenthealth.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
public class JacksonConfiguration {

    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addDateTimeFormatter() {
        return builder -> {
            String dateTimeFormat = dateFormat + " " + timeFormat;

            DateTimeFormatter dt = ofPattern(dateTimeFormat), d = ofPattern(dateFormat), t = ofPattern(timeFormat);

            builder.simpleDateFormat(dateTimeFormat)
                    .deserializers(new LocalDateTimeDeserializer(dt), new LocalDateDeserializer(d), new LocalTimeDeserializer(t))
                    .serializers(new LocalDateTimeSerializer(dt), new LocalDateSerializer(d), new LocalTimeSerializer(t));
        };
    }

}