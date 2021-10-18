package ru.digitalspirit.asaka.bpm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnCondition {

    private String column;

    private String sort;

    private String operation;

    private String value;

    public static ColumnCondition getSortCondition(String column, String sort) {
        ColumnCondition sortCondition = new ColumnCondition();
        sortCondition.setColumn(column);
        sortCondition.setSort(sort);
        return sortCondition;
    }

    public static ColumnCondition getFilterCondition(String column, String operation, String value) {
        ColumnCondition filterCondition = new ColumnCondition();
        filterCondition.setColumn(column);
        filterCondition.setOperation(operation);
        filterCondition.setValue(value);
        return filterCondition;
    }

    public static ColumnCondition getFilterCondition(String column, String operation) {
        ColumnCondition filterCondition = new ColumnCondition();
        filterCondition.setColumn(column);
        filterCondition.setOperation(operation);
        return filterCondition;
    }
}
