package org.abn.recipe.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            var dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
            builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
        };
    }
}
