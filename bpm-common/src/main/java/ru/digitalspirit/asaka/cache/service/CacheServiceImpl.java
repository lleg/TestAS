package ru.digitalspirit.asaka.cache.service;

import com.ibm.websphere.cache.DistributedMap;
import com.ibm.websphere.cache.EntryInfo;
import org.apache.commons.lang3.SerializationUtils;
import ru.digitalspirit.asaka.exception.NbuRuntimeException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

/**
 * Implementation of CacheService for Websphere Application Server
 */
public class CacheServiceImpl implements CacheService{

    private DistributedMap map;

    private final int priority;

    private final int timeToLive;

    private final int inactivityTime;

    /**
     * Constructs cache service with settings
     * @param jndi Jndi of cache
     * @param priority Priority
     * @param timeToLive Time to live
     * @param inactivityTime Inactivity time
     */
    public CacheServiceImpl(String jndi, int priority, int timeToLive, int inactivityTime) {
        try {
            InitialContext context = new InitialContext();
            map = (DistributedMap) context.lookup(jndi);
        } catch(NamingException e){
            throw new NbuRuntimeException(e);
        }
        this.priority = priority;
        this.timeToLive = timeToLive;
        this.inactivityTime = inactivityTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, Serializable object) {
        map.put(key, SerializationUtils.serialize(object), priority, timeToLive, inactivityTime, EntryInfo.SHARED_PUSH_PULL, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, Serializable object, int timeToLive) {
        map.put(key, SerializationUtils.serialize(object), priority, timeToLive, inactivityTime, EntryInfo.SHARED_PUSH_PULL, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable get(String key) {
        return SerializationUtils.deserialize((byte[]) map.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive(String key) {
        return map.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivate(String key) {
        map.invalidate(key);
    }

}
