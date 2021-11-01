package ru.digitalspirit.asaka.orm.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.util.StringUtils;

import java.sql.Time;

public class TimeOrikaConverter extends BidirectionalConverter<Time,String> {

    @Override
    public String convertTo(Time source, Type<String> destinationType, MappingContext mappingContext) {
        if (source == null) {
            return null;
        }
        return source.toString();
    }

    @Override
    public Time convertFrom(String source, Type<Time> destinationType, MappingContext mappingContext) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        return Time.valueOf(source);
    }

}
