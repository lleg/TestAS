package ru.digitalspirit.asaka.cache.service;

import ru.digitalspirit.asaka.cache.model.LocalCacheEntry;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheLocalServiceImpl implements CacheService {

    private static ConcurrentHashMap<String, LocalCacheEntry> map;

    static {
        map = new ConcurrentHashMap<>();
    }

    private static final int DEFAULT_TIME_TO_LIVE = 7 * 24 * 60 * 60;

    @Override
    public void put(String key, Serializable object) {
        map.put(key, new LocalCacheEntry(object, getTimeToDelete(DEFAULT_TIME_TO_LIVE)));
        cleanOldEntries();
    }

    @Override
    public void put(String key, Serializable object, int timeToLive) {
        map.put(key, new LocalCacheEntry(object, getTimeToDelete(timeToLive)));
        cleanOldEntries();
    }

    @Override
    public Serializable get(String key) {
        return map.get(key).getObject();
    }

    @Override
    public boolean isAlive(String key) {
        return map.containsKey(key);
    }

    @Override
    public void deactivate(String key) {
        map.remove(key);
    }

    private long getTimeToDelete(int timeToLive) {
        return System.currentTimeMillis() + timeToLive * 1000;
    }

    private void cleanOldEntries() {
        long currentTime = System.currentTimeMillis();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, LocalCacheEntry> item = (Map.Entry<String, LocalCacheEntry>) it.next();
            if (item != null && item.getValue() != null && item.getValue().getTimeToDelete() < currentTime) {
                it.remove();
            }
        }
    }

}
