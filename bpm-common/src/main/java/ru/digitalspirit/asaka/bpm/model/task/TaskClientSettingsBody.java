package ru.digitalspirit.asaka.bpm.model.task;

import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

/**
 * Created by IShokin on 11.08.2017.
 */
public class TaskClientSettingsBody extends RestEntity {

    //A string which represent the url for human task.
    @SerializedName("url")
    private String url;


    /**
     * @return A string which represent the url for human task.
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String result) {
        this.url = result;
    }

}
