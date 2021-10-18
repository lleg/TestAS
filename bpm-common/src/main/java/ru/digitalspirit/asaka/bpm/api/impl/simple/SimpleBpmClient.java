package ru.digitalspirit.asaka.bpm.api.impl.simple;

import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.digitalspirit.asaka.bpm.api.client.*;
import ru.digitalspirit.asaka.bpm.model.common.SessionCookieInfo;
import ru.digitalspirit.asaka.bpm.api.client.*;
import ru.digitalspirit.asaka.bpm.api.helper.JSessionIdHelper;
import ru.digitalspirit.asaka.bpm.api.impl.SessionResponseInterceptor;
import ru.digitalspirit.asaka.bpm.util.SafeUriBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * Simple implementation of {@link BpmClient} which supports {@link BasicScheme} authentification.
 */
@Immutable
public class SimpleBpmClient implements BpmClient {

    private static final int TOTAL_CONN = 20;
    private static final int ROUTE_CONN = 10;

    private static final String ROOT_ENDPOINT = "rest/bpm/wle/v1";
    private static final String EXPOSED_ENDPOINT = "exposed";
    private static final String PROCESS_ENDPOINT = "process";
    private static final String TASK_ENDPOINT = "task";
    private static final String TASKS_QUERY_ENDPOINT = "tasks";
    private static final String TASKS_TEMPLATE_QUERY_ENDPOINT = "taskTemplates";
    private static final String PROCESS_QUERY_ENDPOINT = "processes";
    private static final String PROCESS_APPS_ENDPOINT = "processApps";
    private static final String SERVICE_ENDPOINT = "service";

    private ExposedClient exposedClient;
    private ProcessClient processClient;
    private TaskClient taskClient;
    private ProcessAppsClient processAppsClient;
    private ServiceClient serviceClient;

    private QueryClient taskQueryClient;
    private QueryClient taskTemplateQueryClient;
    private QueryClient processQueryClient;

    private OrganisationClient organisationClient;

    private static Logger logger = LoggerFactory.getLogger(SimpleBpmClient.class.getName());
    private final CloseableHttpClient httpClient;
    private final URI rootUri;
    private HttpClientContext httpContext;


    public SimpleBpmClient(URI serverUri, String user, String password) {
        logger.info("Start creating bpm client.");
        this.rootUri = new SafeUriBuilder(serverUri).addPath(ROOT_ENDPOINT).build();
        this.httpClient = createClient(user, password);
        logger.info("Bpm client created.");
    }

    public SimpleBpmClient(URI serverUri, String user, String password, JSessionIdHelper sessionHelper) {
        logger.info("Start creating bpm client.");
        this.rootUri = new SafeUriBuilder(serverUri).addPath(ROOT_ENDPOINT).build();
        this.httpClient = createClient(user, password, sessionHelper);
        logger.info("Bpm client created.");
    }

    protected CloseableHttpClient createClient(String user, String password) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(TOTAL_CONN);
        cm.setDefaultMaxPerRoute(ROUTE_CONN);

        logger.info("Pooling connection manager created.");

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
        logger.info("Default credentials provider created.");

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();

        authCache.put(new HttpHost(rootUri.getHost(), rootUri.getPort(), rootUri.getScheme()), basicAuth);
        logger.info("Auth cache created.");

        httpContext = HttpClientContext.create();
        httpContext.setCredentialsProvider(credentialsProvider);
        httpContext.setAuthCache(authCache);
        logger.info("HttpContext filled with Auth cache.");

        return HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).setConnectionManager(cm).build();
    }

    protected CloseableHttpClient createClient(String user, String password, JSessionIdHelper sessionHelper) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(TOTAL_CONN);
        cm.setDefaultMaxPerRoute(ROUTE_CONN);

        logger.info("Pooling connection manager created.");

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
        logger.info("Default credentials provider created.");

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();

        authCache.put(new HttpHost(rootUri.getHost(), rootUri.getPort(), rootUri.getScheme()), basicAuth);
        logger.info("Auth cache created.");

        httpContext = HttpClientContext.create();
        httpContext.setCredentialsProvider(credentialsProvider);
        httpContext.setAuthCache(authCache);
        logger.info("HttpContext filled with Auth cache.");

        SessionResponseInterceptor sessionResponseInterceptor = new SessionResponseInterceptor(sessionHelper);

        SessionCookieInfo sessionCookieInfo = sessionHelper.getSessionCookieInfo();
        BasicCookieStore cookieStore = new BasicCookieStore();
        if (sessionCookieInfo != null) {
            BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", sessionCookieInfo.getJSessionId());
            cookie.setPath(sessionCookieInfo.getPath());
            cookie.setSecure(true);
            cookie.setDomain(sessionCookieInfo.getDomain());
            cookieStore.addCookie(cookie);
        }

        return HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).setConnectionManager(cm).setDefaultCookieStore(cookieStore).addInterceptorLast(sessionResponseInterceptor).build();
    }

    @Override
    public ExposedClient getExposedClient() {
        if (exposedClient == null) {
            exposedClient = new ExposedClientImpl(new SafeUriBuilder(rootUri).addPath(EXPOSED_ENDPOINT).build(), httpClient, httpContext);
        }
        return exposedClient;
    }

    @Override
    public ProcessClient getProcessClient() {
        if (processClient == null) {
            processClient = new ProcessClientImpl(new SafeUriBuilder(rootUri).addPath(PROCESS_ENDPOINT).build(), httpClient, httpContext);
        }
        return processClient;
    }

    @Override
    public TaskClient getTaskClient() {
        if (taskClient == null) {
            taskClient = new TaskClientImpl(new SafeUriBuilder(rootUri).addPath(TASK_ENDPOINT).build(), httpClient, httpContext);
        }
        return taskClient;
    }

    @Override
    public ProcessAppsClient getProcessAppsClient() {
        if (processAppsClient == null) {
            processAppsClient = new ProcessAppsClientImpl(new SafeUriBuilder(rootUri).addPath(PROCESS_APPS_ENDPOINT).build(), httpClient, httpContext);
        }
        return processAppsClient;
    }

    @Override
    public ServiceClient getServiceClient() {
        if (serviceClient == null) {
            serviceClient = new ServiceClientImpl(new SafeUriBuilder(rootUri).addPath(SERVICE_ENDPOINT).build(), httpClient, httpContext);
        }
        return serviceClient;
    }

    @Override
    public QueryClient getTaskQueryClient() {
        if (taskQueryClient == null) {
            taskQueryClient = new QueryClientImpl(new SafeUriBuilder(rootUri).addPath(TASKS_QUERY_ENDPOINT).build(), httpClient, httpContext);
        }
        return taskQueryClient;
    }

    @Override
    public QueryClient getTaskTemplateQueryClient() {
        if (taskTemplateQueryClient == null) {
            taskTemplateQueryClient = new QueryClientImpl(new SafeUriBuilder(rootUri).addPath(TASKS_TEMPLATE_QUERY_ENDPOINT).build(), httpClient, httpContext);
        }
        return taskTemplateQueryClient;
    }

    @Override
    public QueryClient getProcessQueryClient() {
        if (processQueryClient == null) {
            processQueryClient = new QueryClientImpl(new SafeUriBuilder(rootUri).addPath(PROCESS_QUERY_ENDPOINT).build(), httpClient, httpContext);
        }
        return processQueryClient;
    }

    @Override
    public OrganisationClient getOrganisationClient() {
        if (organisationClient == null) {
            organisationClient = new OrganisationClientImpl(new SafeUriBuilder(rootUri).build(), httpClient, httpContext);
        }
        return organisationClient;
    }


    @Override
    public void close() throws IOException {
        httpClient.close();
    }


}
