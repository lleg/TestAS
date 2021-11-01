package ru.digitalspirit.asaka.orm.configuration;

import org.hibernate.dialect.Oracle12cDialect;

import java.sql.Types;

public class Oracle12cDialectExtended extends Oracle12cDialect {

    public Oracle12cDialectExtended() {
        super();
        registerColumnType(Types.DOUBLE, "float");
    }
}