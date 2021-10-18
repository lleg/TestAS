package ru.digitalspirit.asaka.bpm.api.helper;

import ru.digitalspirit.asaka.bpm.model.common.SessionCookieInfo;

public interface JSessionIdHelper {

    SessionCookieInfo getSessionCookieInfo();

    void updateSessionCookieInfo(SessionCookieInfo sessionCookieInfo);

}
