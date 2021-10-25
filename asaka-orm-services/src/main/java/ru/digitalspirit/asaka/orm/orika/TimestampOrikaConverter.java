package ru.digitalspirit.asaka.orm.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;

public class TimestampOrikaConverter extends BidirectionalConverter<Timestamp,String> {

    @Override
    public String convertTo(Timestamp source, Type<String> destinationType, MappingContext mappingContext) {
        if (source == null) {
            return null;
        }
        return source.toString();
    }

    @Override
    public Timestamp convertFrom(String source, Type<Timestamp> destinationType, MappingContext mappingContext) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        return Timestamp.valueOf(source);
    }

}
