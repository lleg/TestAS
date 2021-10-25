package ru.digitalspirit.asaka.orm.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.util.StringUtils;

import java.sql.Date;

public class DateOrikaConverter  extends BidirectionalConverter<Date,String> {

    @Override
    public String convertTo(Date source, Type<String> destinationType, MappingContext mappingContext) {
        if (source == null) {
            return null;
        }
        return source.toString();
    }

    @Override
    public Date convertFrom(String source, Type<Date> destinationType, MappingContext mappingContext) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        return Date.valueOf(source);
    }

}
