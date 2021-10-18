package ru.digitalspirit.asaka.util;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.querydsl.QSort;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;
import ru.digitalspirit.asaka.exception.NbuRuntimeException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuerydslUtil {

    private QuerydslUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final int IN_MAX_EXPRESSION_COUNT = 1000;

    public static QSort getSort(List<ColumnCondition> conditions, EntityPathBase base) {
        List<OrderSpecifier<?>> sortOrder = new ArrayList<>();
        for (ColumnCondition condition : conditions) {
            if (!StringUtil.isEmpty(condition.getSort())) {
                Order order = "desc".equalsIgnoreCase(condition.getSort()) ? Order.DESC : Order.ASC;
                sortOrder.add(new OrderSpecifier(order, getFieldByName(condition.getColumn(), base)));
            }
        }
        if (!sortOrder.isEmpty()) {
            return new QSort(sortOrder);
        }
        return null;
    }

    public static BooleanExpression createExpression(List<ColumnCondition> columnConditions, EntityPathBase base) throws ParseException {
        BooleanExpression ex = null;
        for (ColumnCondition columnCondition : columnConditions) {
            if (!StringUtil.isEmpty(columnCondition.getOperation())) {
                if (ex != null) {
                    ex = ex.and(getOperationExpressionBase(getFieldByName(columnCondition.getColumn(), base), columnCondition));
                } else {
                    ex = getOperationExpressionBase(getFieldByName(columnCondition.getColumn(), base), columnCondition);
                }
            }
        }
        return ex;
    }

    public static BooleanExpression createExpressionOr(List<ColumnCondition> columnConditions, EntityPathBase base) throws ParseException {
        BooleanExpression ex = null;
        for (ColumnCondition columnCondition : columnConditions) {
            if (!StringUtil.isEmpty(columnCondition.getOperation())) {
                if (ex != null) {
                    ex = ex.or(getOperationExpressionBase(getFieldByName(columnCondition.getColumn(), base), columnCondition));
                } else {
                    ex = getOperationExpressionBase(getFieldByName(columnCondition.getColumn(), base), columnCondition);
                }
            }
        }
        return ex;
    }

    private static BooleanExpression getOperationExpressionBase(ComparableExpressionBase cExB, ColumnCondition columnCondition) throws ParseException {
        if (cExB instanceof ComparableExpression) {
            return getOperationExpression((ComparableExpression) cExB, columnCondition);
        } else if (cExB instanceof NumberExpression) {
            return getOperationNumberExpression((NumberExpression) cExB, columnCondition);
        }
        return null;
    }

    private static BooleanExpression getOperationExpression(ComparableExpression cEx, ColumnCondition columnCondition) {
        Comparable value = getObjectValue(cEx, columnCondition.getValue());
        if ("=".equals(columnCondition.getOperation())) {
            return cEx.eq(value);
        } else if (">".equals(columnCondition.getOperation())) {
            return cEx.gt(value);
        } else if (">=".equals(columnCondition.getOperation())) {
            return cEx.goe(value);
        } else if ("<".equals(columnCondition.getOperation())) {
            return cEx.lt(value);
        } else if ("<=".equals(columnCondition.getOperation())) {
            return cEx.loe(value);
        } else if ("%".equals(columnCondition.getOperation())) {
            return ((StringExpression) cEx).containsIgnoreCase((String) value);
        } else if ("in".equals(columnCondition.getOperation())) {
            return getInExpression(cEx, Arrays.asList(((String) value).split(",")));
        } else if ("eq".equals(columnCondition.getOperation())) {
            return ((StringExpression) cEx).equalsIgnoreCase((String) value);
        } else if ("null".equals(columnCondition.getOperation())) {
            return cEx.isNull();
        } else if ("notNull".equals(columnCondition.getOperation())) {
            return cEx.isNotNull();
        }
        return null;
    }

    private static BooleanExpression getOperationNumberExpression(NumberExpression nEx, ColumnCondition columnCondition) throws ParseException {
        Number value = getNumber((NumberPath) nEx, columnCondition.getValue());
        if ("=".equals(columnCondition.getOperation())) {
            return nEx.eq(value);
        } else if (">".equals(columnCondition.getOperation())) {
            return nEx.gt(value);
        } else if (">=".equals(columnCondition.getOperation())) {
            return nEx.goe(value);
        } else if ("<".equals(columnCondition.getOperation())) {
            return nEx.lt(value);
        } else if ("<=".equals(columnCondition.getOperation())) {
            return nEx.loe(value);
        } else if ("eq".equals(columnCondition.getOperation())) {
            return nEx.eq(value);
        } else if ("null".equals(columnCondition.getOperation())) {
            return nEx.isNull();
        } else if ("notNull".equals(columnCondition.getOperation())) {
            return nEx.isNotNull();
        }
        return null;
    }

    private static Number getNumber(NumberPath nEx, String value) {
        if (nEx.getType().equals(Long.class)) {
            return Long.valueOf(value);
        }
        if (nEx.getType().equals(Integer.class)) {
            return Integer.valueOf(value);
        }
        if (nEx.getType().equals(Double.class)) {
            return Double.valueOf(value);
        }
        if (nEx.getType().equals(BigInteger.class)) {
            return new BigInteger(value);
        }
        if (nEx.getType().equals(BigDecimal.class)) {
            return new BigDecimal(value);
        }
        throw new NbuRuntimeException("Unknown number type!");
    }

    private static Comparable getObjectValue(ComparableExpression cEx, String value) {
        if (cEx instanceof DateTimePath) {
            //return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(value);
            return Timestamp.valueOf(value);
        }
        return value;
    }

    private static ComparableExpressionBase getFieldByName(String fieldName, EntityPathBase base) {
        try {
            Field field = base.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (ComparableExpressionBase) field.get(base);
        } catch (Exception ex) {
            return null;
        }
    }

    private static BooleanExpression getInExpression(ComparableExpression cEx, List<String> values) {
        BooleanExpression inExpression = null;
        for (int i = 0; i < values.size(); i += IN_MAX_EXPRESSION_COUNT) {
            int lastIndex = i + IN_MAX_EXPRESSION_COUNT > values.size() ? values.size() : i + IN_MAX_EXPRESSION_COUNT;
            inExpression = inExpression == null ? cEx.in(values.subList(i, lastIndex)) : inExpression.or(cEx.in(values.subList(i, lastIndex)));
        }
        return inExpression;
    }

}
