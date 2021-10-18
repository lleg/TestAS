package ru.digitalspirit.asaka.bpm.api.impl;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.HttpContext;
import ru.digitalspirit.asaka.bpm.model.common.SessionCookieInfo;
import ru.digitalspirit.asaka.bpm.api.helper.JSessionIdHelper;

import java.io.IOException;

public class SessionResponseInterceptor implements HttpResponseInterceptor {

    private JSessionIdHelper sessionHelper;

    private static final String COOKIE_HEADER = "Set-Cookie";
    private static final String COOKIE_ATTRIBUTE = "http.cookie-store";
    private static final String JSESSION_KEY = "JSESSIONID";

    public SessionResponseInterceptor(JSessionIdHelper sessionHelper) {
        this.sessionHelper = sessionHelper;
    }

    @Override
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        if (!isAuthenticated(response)) {
            updateSessionId(context);
        }
    }

    private boolean isAuthenticated(HttpResponse response) {
        for (Header sessionCookieHeader : response.getHeaders(COOKIE_HEADER)) {
            if (sessionCookieHeader.getValue().contains(JSESSION_KEY)) {
                return false;
            }
        }
        return true;
    }

    private void updateSessionId(HttpContext context) {
        BasicCookieStore basicCookieStore = (BasicCookieStore) context.getAttribute(COOKIE_ATTRIBUTE);
        for (Cookie cookie : basicCookieStore.getCookies()) {
            if (JSESSION_KEY.equals(cookie.getName()) && cookie.getDomain() != null) {
                SessionCookieInfo sessionCookieInfo = new SessionCookieInfo();
                sessionCookieInfo.setJSessionId(cookie.getValue());
                sessionCookieInfo.setDomain(cookie.getDomain());
                sessionCookieInfo.setPath(cookie.getPath());
                sessionHelper.updateSessionCookieInfo(sessionCookieInfo);
                break;
            }
        }
    }

}
