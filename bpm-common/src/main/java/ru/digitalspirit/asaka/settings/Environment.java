package ru.digitalspirit.asaka.settings;

import javax.naming.InitialContext;
import javax.naming.NamingException;


// TODO: Change in enum and service. Need refactoring.
public class Environment {

    private Environment() {
        throw new IllegalStateException("Configuration class");
    }

    private static final String PREFIX = "java:comp/env/";

    public static final String CACHE_JNDI = "CacheJndi";

    public static final String CACHE_PRIORITY = "SessionCachePriority";

    public static final String CACHE_TIME_TO_LIVE = "SessionCacheTimeToLiveSeconds";

    public static final String CACHE_INACTIVITY_TIME = "SessionCacheInactivityTimeSeconds";

    public static final String BPM_PERFORMANCE_DATA_WAREHOUSE_DATASOURCE_JNDI = "bpmPerformanceJNDI";

    public static String getCacheJndi() {
        return lookupEnvString(CACHE_JNDI);
    }

    public static Integer getCachePriority() {
        return lookupEnvInteger(CACHE_PRIORITY);
    }

    public static Integer getCacheTimeToLive() {
        return lookupEnvInteger(CACHE_TIME_TO_LIVE);
    }

    public static Integer getCacheInactivityTime() {
        return lookupEnvInteger(CACHE_INACTIVITY_TIME);
    }

    public static String getBpmPerformanceDataWarehouseDatasourceJndi() {
        return lookupEnvString(BPM_PERFORMANCE_DATA_WAREHOUSE_DATASOURCE_JNDI);
    }

    private static String lookupEnvString(String name) {
        return (String) lookupEnv(name);
    }

    private static Integer lookupEnvInteger(String name) {
        return (Integer) lookupEnv(name);
    }

    private static Object lookupEnv(String name) {
        try {
            return new InitialContext().lookup(PREFIX + name);
        } catch (NamingException e) {
            throw new IllegalStateException("Can't obtain variable: " + name + "\n", e);
        }
    }
}
