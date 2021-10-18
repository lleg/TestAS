package ru.digitalspirit.asaka.bpm.model.other.exposed;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import ru.digitalspirit.asaka.bpm.model.common.RestEntity;

import java.util.List;

public class ExposedBody extends RestEntity {

    private static final List<Item> EMPTY_LIST = Lists.newArrayList();

    @SerializedName("exposedItemsList")
    private List<Item> exposedItemsList = Lists.newArrayList();

    //To avoid null checks after deserialization.
    public List<Item> getExposedItemsList() {
        return MoreObjects.firstNonNull(exposedItemsList, EMPTY_LIST);
    }

}
