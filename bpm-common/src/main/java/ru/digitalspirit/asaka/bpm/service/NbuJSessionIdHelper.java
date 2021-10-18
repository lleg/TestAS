package ru.digitalspirit.asaka.bpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.digitalspirit.asaka.bpm.api.helper.JSessionIdHelper;
import ru.digitalspirit.asaka.bpm.model.common.SessionCookieInfo;
import ru.digitalspirit.asaka.cache.service.CacheService;
import ru.digitalspirit.asaka.exception.NbuRuntimeException;

/**
 * Helper for JSession
 */
public class NbuJSessionIdHelper implements JSessionIdHelper {

    @Autowired
    private CacheService cacheService;

    private static String JSESSIONID_KEY = "NBU_JSESSIONID";

    /**
     * Call current session cookie info
     * @return Session cookie info
     */
    @Override
    public SessionCookieInfo getSessionCookieInfo() {
        try {
            return (SessionCookieInfo)cacheService.get(JSESSIONID_KEY);
        } catch (NullPointerException | IllegalArgumentException e) {
            return null;
        } catch (Exception e) {
            throw new NbuRuntimeException("Error on receiving JSESSIONID from cache", e);
        }
    }

    /**
     * Update current session cookie info
     * @param sessionCookieInfo Session cookie info
     */
    @Override
    public void updateSessionCookieInfo(SessionCookieInfo sessionCookieInfo) {
        SessionCookieInfo currentSessionCookieInfo = getSessionCookieInfo();
        if (sessionCookieInfo != null && (currentSessionCookieInfo == null || !sessionCookieInfo.getJSessionId().equals(currentSessionCookieInfo.getJSessionId()))) {
            cacheService.put(JSESSIONID_KEY, sessionCookieInfo);
        }
    }
}
