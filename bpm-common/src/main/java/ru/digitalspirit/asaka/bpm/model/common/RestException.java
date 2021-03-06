package ru.digitalspirit.asaka.bpm.model.common;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static ru.digitalspirit.asaka.bpm.util.Constants.*;

public class RestException extends RuntimeException {

    //The status of the previous API call.
    @SerializedName("status")
    private String status;

    //The classname associated with the exception.
    @SerializedName("exceptionType")
    private String exceptionType;

    //Message ID of the exception.
    @SerializedName("errorNumber")
    private String errorNumber;

    //Message text of the exception.
    @SerializedName("errorMessage")
    private String errorMessage;

    //Message text parameters of the exception.
    @SerializedName("errorMessageParameters")
    private List<String> errorMessageParameters = Lists.newArrayList();

    //The stacktrace associated with the exception. Note that this will be omitted unless the "server-stacktrace-enabled" property is enabled in the server's 100Custom.xml file.
    @SerializedName("programmersDetails")
    private String programmersDetails;

    //Prior responses. Set if a bulk command was used.
    @SerializedName("responses")
    private String responses;

    RestException() {}

    @Override
    @SuppressWarnings("StringBufferReplaceableByString")
    public String getMessage() {
        StringBuilder exceptionBuilder = new StringBuilder();
        exceptionBuilder.append(this.getClass().getName());
        exceptionBuilder.append(COLON).append(SPACE);
        exceptionBuilder.append(errorMessage).append(SPACE);
        exceptionBuilder.append(OPEN_BRACKET).append("Root cause");
        exceptionBuilder.append(COLON).append(SPACE).append(exceptionType);
        exceptionBuilder.append(CLOSE_BRACKET);
        return exceptionBuilder.toString();
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    @Override
    public String toString() {
        return getMessage();
    }

    /**
     * @return The status of the previous API call.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return The classname associated with the exception.
     */
    public String getExceptionType() {
        return exceptionType;
    }

    /**
     * @return Message ID of the exception.
     */
    public String getErrorNumber() {
        return errorNumber;
    }

    /**
     * @return Message text of the exception.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return Message text parameters of the exception.
     */
    public List<String> getErrorMessageParameters() {
        return errorMessageParameters;
    }

    /**
     * @return The stacktrace associated with the exception.
     *         Note that this will be omitted unless the "server-stacktrace-enabled" property is enabled in the server's 100Custom.xml file.
     */
    public String getProgrammersDetails() {
        return programmersDetails;
    }

    /**
     * @return Prior responses.  Set if a bulk command was used.
     */
    public String getResponses() {
        return responses;
    }
}
