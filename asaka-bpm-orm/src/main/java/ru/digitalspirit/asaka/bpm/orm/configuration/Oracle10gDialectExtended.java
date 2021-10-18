package ru.digitalspirit.asaka.bpm.orm.configuration;

import org.hibernate.dialect.Oracle10gDialect;

import java.sql.Types;

public class Oracle10gDialectExtended extends Oracle10gDialect {

    public Oracle10gDialectExtended() {
        super();
        registerColumnType(Types.DOUBLE, "float");
    }
}