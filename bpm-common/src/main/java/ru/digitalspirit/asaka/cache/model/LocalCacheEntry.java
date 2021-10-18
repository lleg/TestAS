package ru.digitalspirit.asaka.cache.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class LocalCacheEntry {

    private Serializable object;

    private long timeToDelete;

}
