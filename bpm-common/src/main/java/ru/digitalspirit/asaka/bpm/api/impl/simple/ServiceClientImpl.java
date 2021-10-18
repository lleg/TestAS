package ru.digitalspirit.asaka.bpm.api.impl.simple;

import com.google.gson.Gson;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.digitalspirit.asaka.bpm.model.service.ServiceData;
import ru.digitalspirit.asaka.bpm.api.client.ServiceClient;
import ru.digitalspirit.asaka.bpm.exception.BpmApiException;
import ru.digitalspirit.asaka.bpm.util.SafeUriBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URI;
import java.util.Map;


public class ServiceClientImpl extends BaseClient implements ServiceClient {

    private final URI rootUri;
    private final HttpClient httpClient;
    private final HttpContext httpContext;

    private static final String ACTION = "action";
    private static final String PARAMS = "params";

    private static final String ACTION_START = "start";

    private static Logger logger = LoggerFactory.getLogger(ServiceClientImpl.class.getName());

    ServiceClientImpl(URI rootUri, HttpClient httpClient, HttpContext httpContext) {
        this.httpClient = httpClient;
        this.rootUri = rootUri;
        this.httpContext = httpContext;
    }

    ServiceClientImpl(URI rootUri, HttpClient httpClient) {
        this(rootUri, httpClient, null);
    }

    @Override
    public ServiceData startService(@Nonnull String sId, @Nullable Map<String, Object> input) {
        sId = nonNull(sId, "sId can't be null");
        Gson gson = getGson();

        SafeUriBuilder uri = new SafeUriBuilder(rootUri).addPath(sId).addParameter(ACTION, ACTION_START);

        if (input != null && input.size() > 0) {
            uri.addParameter(PARAMS, gson.toJson(input));
        }

        HttpPost request = new HttpPost(uri.build());
        setRequestTimeOut(request, DEFAULT_TIMEOUT);
        setHeadersPost(request);

        logRequest(request, null);

        String body;
        HttpResponse response;

        try {
            response = httpContext == null ? httpClient.execute(request) : httpClient.execute(request, httpContext);
            body = IOUtils.toString(response.getEntity().getContent(), Charsets.UTF_8);
        } catch (IOException e) {
            logger.error("Can't get ServiceData object from Server with uri: " + uri, e);
            throw new BpmApiException("Can't get ServiceData object from Server with uri: " + uri, e);
        }

        logResponse(response, body);
        request.releaseConnection();

        return gson.fromJson(body, ServiceData.class);
    }

}
