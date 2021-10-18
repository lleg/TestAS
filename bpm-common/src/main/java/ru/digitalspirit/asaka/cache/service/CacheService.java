package ru.digitalspirit.asaka.cache.service;

import java.io.Serializable;

/**
 * Service for working with cache
 */
public interface CacheService {

    /**
     * Put object to cache
     * @param key Key
     * @param object Object
     */
    void put(String key, Serializable object);

    /**
     * Put object to cache
     * @param key Key
     * @param object Object
     * @param timeToLive Time to live in seconds
     */
    void put(String key, Serializable object, int timeToLive);

    /**
     * Receive object from cache
     * @param key Key
     * @return Object
     */
    Serializable get(String key);

    /**
     * Check whether the object is alive
     * @param key Key
     * @return Is alive
     */
    boolean isAlive(String key);

    /**
     * Delete object from cache
     * @param key Key
     */
    void deactivate(String key);

}
