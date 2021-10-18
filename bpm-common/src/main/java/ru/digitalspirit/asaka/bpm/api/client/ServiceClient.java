package ru.digitalspirit.asaka.bpm.api.client;

import ru.digitalspirit.asaka.bpm.model.service.ServiceData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * Client for service api actions.
 */
public interface ServiceClient {

    /**
     * Start a new instance of a service.
     * @param sid The id of the Service to be used.
     * @param input Input parameters of the service
     * @return the detailed service information (see {@link ServiceData})
     */
    ServiceData startService(@Nonnull String sid, @Nullable Map<String, Object> input);

   /* public ServiceData resumeService(@Nonnull String sid);

    public ServiceData stopService(@Nonnull String sid);*/
}
