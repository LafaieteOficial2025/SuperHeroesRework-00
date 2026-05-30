package com.fiskheroesrebirth.mod.data;

public final class DataKey<T> {
    public final int id;
    public final String name;
    public final DataSerializer<T> serializer;
    public final T defaultValue;

    public DataKey(int id, String name, DataSerializer<T> ser, T def) {
        this.id = id;
        this.name = name;
        this.serializer = ser;
        this.defaultValue = def;
    }

    @Override
    public int hashCode() { return id; }
}
