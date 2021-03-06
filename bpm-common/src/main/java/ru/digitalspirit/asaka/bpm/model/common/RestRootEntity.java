package ru.digitalspirit.asaka.bpm.model.common;

import com.google.gson.annotations.SerializedName;

import static ru.digitalspirit.asaka.bpm.util.Constants.*;

public class RestRootEntity<T extends Describable> extends RestEntity {

    //The status of the API call.
    @SerializedName("status")
    private String status;

    //Success API call data.
    @SerializedName("data")
    private T payload;

    //Unsuccess API call information.
    @SerializedName("Data")
    private RestException exception;

    /**
     * @return true if the API call was unsuccessfull, and {@link RestRootEntity} contains exception information.
     */
    public boolean isExceptional() {
        return exception != null;
    }

    /**
     * @return Success API call data.
     * @throws RestException if the API call was unsuccessfull, and {@link RestRootEntity}
     *                       contain any exception details.
     */
    public T getPayload() {
        if (isExceptional()) {
            throw exception;
        }
        return payload;
    }

    /**
     * @return The status of the API call.
     */
    public String getStatus() {
        return status;
    }

    @Override
    @SuppressWarnings("StringBufferReplaceableByString")
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName());
        builder.append(COLON).append(SPACE);
        builder.append("Status").append(COLON).append(SPACE);
        builder.append(status).append(SEMICOLON).append(SPACE);
        builder.append("IsExceptional").append(COLON).append(SPACE);
        builder.append((isExceptional() ? PASSES : FAILS));
        return builder.toString();
    }
}
