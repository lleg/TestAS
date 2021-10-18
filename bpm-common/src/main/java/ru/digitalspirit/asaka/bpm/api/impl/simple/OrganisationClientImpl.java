package ru.digitalspirit.asaka.bpm.api.impl.simple;

import com.google.gson.Gson;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.digitalspirit.asaka.bpm.model.organisation.GroupData;
import ru.digitalspirit.asaka.bpm.model.organisation.GroupDataList;
import ru.digitalspirit.asaka.bpm.model.organisation.UserData;
import ru.digitalspirit.asaka.bpm.api.client.OrganisationClient;
import ru.digitalspirit.asaka.bpm.exception.BpmApiException;
import ru.digitalspirit.asaka.bpm.util.SafeUriBuilder;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URI;

public class OrganisationClientImpl extends BaseClient implements OrganisationClient {

    private final URI rootUri;
    private final HttpClient httpClient;
    private final HttpContext httpContext;

    private static final String USER_ENDPOINT = "user";
    private static final String GROUPS_ENDPOINT = "groups";
    private static final String GROUP_ENDPOINT = "group";

    private static final String USER = "userName";
    private static final String INTERNAL_GROUPS = "includeInternalMemberships";

    private static Logger logger = LoggerFactory.getLogger(OrganisationClientImpl.class.getName());

    OrganisationClientImpl(URI rootUri, HttpClient httpClient, HttpContext httpContext) {
        this.httpClient = httpClient;
        this.rootUri = rootUri;
        this.httpContext = httpContext;
    }

    OrganisationClientImpl(URI rootUri, HttpClient httpClient) {
        this(rootUri, httpClient, null);
    }

    @Override
    public UserData getUserByName(@Nonnull String userName, @Nonnull boolean includeInternalMembership) {
        userName = nonNull(userName, "userName can't be null");

        SafeUriBuilder uri = new SafeUriBuilder(rootUri).addPath(USER_ENDPOINT).addParameter(USER, userName).addParameter(INTERNAL_GROUPS, includeInternalMembership);

        HttpGet request = new HttpGet(uri.build());
        setRequestTimeOut(request, DEFAULT_TIMEOUT);
        setHeadersGet(request);

        logRequest(request, null);

        String body;
        HttpResponse response;

        try {
            response = httpContext == null ? httpClient.execute(request) : httpClient.execute(request, httpContext);
            body = IOUtils.toString(response.getEntity().getContent(), Charsets.UTF_8);
        } catch (IOException e) {
            logger.error("Can't get UserData object from Server with uri: " + uri, e);
            throw new BpmApiException("Can't get UserData object from Server with uri: " + uri, e);
        }

        logResponse(response, body);
        request.releaseConnection();

        Gson gson = getGson();
        return gson.fromJson(body, UserData.class);
    }

    @Override
    public GroupDataList getGroups() {
        SafeUriBuilder uri = new SafeUriBuilder(rootUri).addPath(GROUPS_ENDPOINT);

        HttpGet request = new HttpGet(uri.build());
        setRequestTimeOut(request, DEFAULT_TIMEOUT);
        setHeadersGet(request);

        logRequest(request, null);

        String body;
        HttpResponse response;

        try {
            response = httpContext == null ? httpClient.execute(request) : httpClient.execute(request, httpContext);
            body = IOUtils.toString(response.getEntity().getContent(), Charsets.UTF_8);
        } catch (IOException e) {
            logger.error("Can't get GroupDataList object from Server with uri: " + uri, e);
            throw new BpmApiException("Can't get GroupDataList object from Server with uri: " + uri, e);
        }

        logResponse(response, body);
        request.releaseConnection();

        Gson gson = getGson();
        return gson.fromJson(body, GroupDataList.class);
    }

    @Override
    public GroupData getGroupByName(@Nonnull String groupName) {
        groupName = nonNull(groupName, "groupName can't be null");

        SafeUriBuilder uri = new SafeUriBuilder(rootUri).addPath(GROUP_ENDPOINT).setPathId(groupName);

        HttpGet request = new HttpGet(uri.build());
        setRequestTimeOut(request, DEFAULT_TIMEOUT);
        setHeadersGet(request);

        logRequest(request, null);

        String body;
        HttpResponse response;

        try {
            response = httpContext == null ? httpClient.execute(request) : httpClient.execute(request, httpContext);
            body = IOUtils.toString(response.getEntity().getContent(), Charsets.UTF_8);
        } catch (IOException e) {
            logger.error("Can't get GroupData object from Server with uri: " + uri, e);
            throw new BpmApiException("Can't get GroupData object from Server with uri: " + uri, e);
        }

        logResponse(response, body);
        request.releaseConnection();

        Gson gson = getGson();
        return gson.fromJson(body, GroupData.class);
    }

}
