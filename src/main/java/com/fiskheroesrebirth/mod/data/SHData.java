package com.fiskheroesrebirth.mod.data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SHData {
    private static final Map<String, DataKey<?>> REGISTRY = new ConcurrentHashMap<>();
    private static int nextId = 0;

    public static final DataKey<String> HERO = register("hero", DataSerializers.STRING, "");
    public static final DataKey<Float> ENERGY = register("energy", DataSerializers.FLOAT, 1000f);
    public static final DataKey<Float> MAX_ENERGY = register("max_energy", DataSerializers.FLOAT, 1000f);
    public static final DataKey<Boolean> FLYING = register("flying", DataSerializers.BOOLEAN, false);
    public static final DataKey<Boolean> SUIT_ACTIVE = register("suit_active", DataSerializers.BOOLEAN, false);
    public static final DataKey<Integer> SUIT_TIER = register("suit_tier", DataSerializers.INT, 1);

    public static <T> DataKey<T> register(String id, DataSerializer<T> ser, T def) {
        DataKey<T> key = new DataKey<>(nextId++, id, ser, def);
        REGISTRY.put(id, key);
        return key;
    }

    public static DataKey<?> get(String id) { return REGISTRY.get(id); }
    public static Collection<DataKey<?>> getAll() { return REGISTRY.values(); }
}
