package com.fiskheroesrebirth.mod.data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataHolder {
    private final Map<Integer, Object> values = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T get(DataKey<T> key) {
        Object v = values.get(key.id);
        return v  key.defaultValue;
    }

    public <T> void set(DataKey<T> key, T value) {
        values.put(key.id, value);
    }
}
