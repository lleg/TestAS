package ru.digitalspirit.asaka.bpm.api.impl;


import ru.digitalspirit.asaka.bpm.api.impl.simple.KerberosBpmClient;
import ru.digitalspirit.asaka.bpm.api.impl.simple.SecuredBpmClient;
import ru.digitalspirit.asaka.bpm.api.impl.simple.SimpleBpmClient;
import ru.digitalspirit.asaka.bpm.api.client.BpmClient;
import ru.digitalspirit.asaka.bpm.api.helper.JSessionIdHelper;

import java.net.URI;

public class BpmClientFactory {

    private static final BpmClientFactory INSTANCE = new BpmClientFactory();

    public static BpmClientFactory getFactory() {
        return INSTANCE;
    }

    private BpmClientFactory() {
    }

    /**
     * Creates the Bpm client object with given parameters.
     *
     * @param serverUri is a absolute server host/port path.
     * @param user      is a login by which the actions will be performed.
     * @param password  is a user password.
     * @return {@link BpmClient} instance.
     */
    public BpmClient createClient(URI serverUri, String user, String password) {
        return new SimpleBpmClient(serverUri, user, password);
    }

    /**
     * Creates the Bpm client object with given parameters.
     *
     * @param serverUri is a absolute server host/port path.
     * @param user      is a login by which the actions will be performed.
     * @param password  is a user password.
     * @param useSSL    shows, if you need use SSL connection.
     * @return {@link BpmClient} instance.
     */
    public BpmClient createClient(URI serverUri, String user, String password, boolean useSSL) {
        if (useSSL) {
            return new SecuredBpmClient(serverUri, user, password);
        } else {
            return new SimpleBpmClient(serverUri, user, password);
        }
    }

    /**
     * Creates the Bpm client object with given parameters.
     *
     * @param serverUri     is a absolute server host/port path.
     * @param user          is a login by which the actions will be performed.
     * @param password      is a user password.
     * @param useSSL        shows, if you need use SSL connection.
     * @param sessionHelper helper for receiving/updating jsessionid.
     * @return {@link BpmClient} instance.
     */
    public BpmClient createClient(URI serverUri, String user, String password, boolean useSSL, JSessionIdHelper sessionHelper) {
        if (useSSL) {
            return new SecuredBpmClient(serverUri, user, password, sessionHelper);
        } else {
            return new SimpleBpmClient(serverUri, user, password);
        }
    }

    /**
     * Creates the Bpm client object with given parameters.
     *
     * @param serverUri is a absolute server host/port path.
     * @param user      is a login by which the actions will be performed.
     * @param password  is a user password.
     * @param domain    is an identification string that defines a realm of administrative autonomy, authority or control.
     * @param kdc       key distribution center (KDC) is part of a cryptosystem intended to reduce the risks inherent in exchanging keys.
     * @return {@link BpmClient} instance.
     */
    public BpmClient createClient(URI serverUri, String user, String password, String domain, String kdc) {
        return new KerberosBpmClient(serverUri, user, password, domain, kdc);
    }

}
