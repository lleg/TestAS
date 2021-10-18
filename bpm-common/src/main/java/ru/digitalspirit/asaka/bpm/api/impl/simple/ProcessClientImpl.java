package ru.digitalspirit.asaka.bpm.api.impl.simple;

import com.google.gson.Gson;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.digitalspirit.asaka.bpm.model.process.MessageEvent;
import ru.digitalspirit.asaka.bpm.model.process.ProcessDetails;
import ru.digitalspirit.asaka.bpm.api.client.ProcessClient;
import ru.digitalspirit.asaka.bpm.exception.BpmApiException;
import ru.digitalspirit.asaka.bpm.util.SafeUriBuilder;

import javax.annotation.Nonnull;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Immutable
class ProcessClientImpl extends BaseClient implements ProcessClient {

    private final URI rootUri;
    private final HttpClient httpClient;
    private final HttpContext httpContext;

    //Request parameters constants
    private static final String ACTION = "action";
    private static final String PROCESS_DEFENITION_ID = "bpdId";
    private static final String SNAPSHOT_ID = "snapshotId";
    private static final String BRANCH_ID = "branchId";
    private static final String PROCESS_APP_ID = "processAppId";
    private static final String PARAMS = "params";
    private static final String MESSAGE = "message";

    //Methods for processes
    private static final String ACTION_START = "start";
    private static final String ACTION_SUSPEND = "suspend";
    private static final String ACTION_RESUME = "resume";
    private static final String ACTION_TERMINATE = "terminate";
    private static final String ACTION_SEND_MESSAGE = "sendMessage";

    private static Logger logger = LoggerFactory.getLogger(ProcessClientImpl.class.getName());


    ProcessClientImpl(URI rootUri, HttpClient httpClient, HttpContext httpContext) {
        this.httpClient = httpClient;
        this.rootUri = rootUri;
        this.httpContext = httpContext;
    }

    ProcessClientImpl(URI rootUri, HttpClient httpClient) {
        this(rootUri, httpClient, null);
    }


    //Will use only one parameter of processAppId, snapshotId or branchId. Which one is not specified.
    @Override
    public ProcessDetails startProcess(@Nonnull String bpdId, String processAppId, String snapshotId, String branchId, Map<String, Object> input) {
        bpdId = nonNull(bpdId, "bpdId can't be null");

        Map<String, String> choise = new HashMap<>();
        choise.put(SNAPSHOT_ID, snapshotId);
        choise.put(BRANCH_ID, branchId);
        choise.put(PROCESS_APP_ID, processAppId);
        Map.Entry<String, String> entry = reduce(choise);

        SafeUriBuilder uri = new SafeUriBuilder(rootUri).addParameter(ACTION, ACTION_START).addParameter(PROCESS_DEFENITION_ID, bpdId).addParameter(entry.getKey(), entry.getValue());

        Gson gson = getGson();
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
            logger.error("Can't start process object from Server with uri: " + rootUri, e);
            throw new BpmApiException("Can't start process object from Server with uri: " + rootUri, e);
        }

        logResponse(response, body);
        request.releaseConnection();
        return gson.fromJson(body, ProcessDetails.class);
    }

    private Map.Entry<String, String> reduce(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                return entry;
            }
        }
        throw new IllegalArgumentException("One of processAppId, snapshotId or branchId must be specified");
    }

    @Override
    public ProcessDetails suspendProcess(@Nonnull String piid) {
        return changeProcessState(piid, ACTION_SUSPEND);
    }

    @Override
    public ProcessDetails resumeProcess(@Nonnull String piid) {
        return changeProcessState(piid, ACTION_RESUME);
    }

    @Override
    public ProcessDetails terminateProcess(@Nonnull String piid) {
        return changeProcessState(piid, ACTION_TERMINATE);
    }

    private ProcessDetails changeProcessState(String piid, String action) {
        piid = nonNull(piid, "piid can't be null");

        URI uri = new SafeUriBuilder(rootUri).addPath(piid).addParameter(ACTION, action).build();

        HttpPost request = new HttpPost(uri);
        setRequestTimeOut(request, DEFAULT_TIMEOUT);
        setHeadersPut(request); //Same headers as for GET/PUT

        logRequest(request, null);

        String body;
        HttpResponse response;

        try {
            response = httpContext == null ? httpClient.execute(request) : httpClient.execute(request, httpContext);
            body = IOUtils.toString(response.getEntity().getContent(), Charsets.UTF_8);
        } catch (IOException e) {
            logger.error("Can't change process state to state: " + action, e);
            throw new BpmApiException("Can't change process state to state: " + action, e);
        }

        logResponse(response, body);
        request.releaseConnection();

        Gson gson = getGson();
        return gson.fromJson(body, ProcessDetails.class);
    }

    @Override
    public ProcessDetails currentState(@Nonnull String piid) {
        piid = nonNull(piid, "piid can't be null");

        URI uri = new SafeUriBuilder(rootUri).addPath(piid).build();

        HttpGet request = new HttpGet(uri);
        setRequestTimeOut(request, DEFAULT_TIMEOUT);
        setHeadersGet(request);

        logRequest(request, null);

        String body;
        HttpResponse response;

        try {
            response = httpContext == null ? httpClient.execute(request) : httpClient.execute(request, httpContext);
            body = IOUtils.toString(response.getEntity().getContent(), Charsets.UTF_8);
        } catch (IOException e) {
            logger.error("Can't get ProcessDetails object from Server with uri: " + uri, e);
            throw new BpmApiException("Can't get ProcessDetails object from Server with uri: " + uri, e);
        }

        logResponse(response, body);
        request.releaseConnection();

        Gson gson = getGson();
        return gson.fromJson(body, ProcessDetails.class);
    }


    public void sendMessage(@Nonnull MessageEvent message) {
        SafeUriBuilder uri = new SafeUriBuilder(rootUri).addParameter(ACTION, ACTION_SEND_MESSAGE).addParameter(MESSAGE, formMessage(message));

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
            logger.error("Can't send message object from Server with uri: " + rootUri, e);
            throw new BpmApiException("Can't send message object from Server with uri: " + rootUri, e);
        }

        logResponse(response, body);
        request.releaseConnection();
    }

    private String formMessage(MessageEvent message) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new BpmApiException(ex);
        }
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("eventmsg");
        doc.appendChild(rootElement);

        Element event = createTextElement("event", nonNull(message.getEventName(), "Event name can't be null"), rootElement, doc);
        event.setAttribute("processApp", nonNull(message.getProcessApp(), "Process app acronym can't be null"));
        if (message.getSnapshot() != null) {
            event.setAttribute("snapshot", message.getSnapshot());
        }
        if (message.getUcaName() != null) {
            event.setAttribute("ucaname", message.getUcaName());
        }

        if (message.getQueue() != null) {
            createTextElement("queue", message.getQueue(), rootElement, doc);
        }

        Map<String, String> parameterMap = nonNull(message.getParameters(), "Parameters can't be null");
        if (parameterMap.isEmpty()) {
            throw new IllegalArgumentException("Parameters can't be empty!");
        }
        Element parameters = doc.createElement("parameters");
        rootElement.appendChild(parameters);
        for (String parameterName : parameterMap.keySet()) {
            Element parameter = doc.createElement("parameter");
            createTextElement("key", parameterName, parameter, doc);
            createTextElement("value", parameterMap.get(parameterName), parameter, doc);
            parameters.appendChild(parameter);
        }
        return documentToString(doc);
    }


    private String documentToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new BpmApiException("Error converting to String", ex);
        }
    }

    private Element createTextElement(String name, String value, Element parentElement, Document doc) {
        Element element = doc.createElement(name);
        parentElement.appendChild(element);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

}
