package com.fiskheroesrebirth.mod.data;

import net.minecraft.nbt.*;
import net.minecraft.network.FriendlyByteBuf;

public final class DataSerializers {
    public static final DataSerializer<Boolean> BOOLEAN = new DataSerializer<>() {
        public void write(FriendlyByteBuf b, Boolean v) { b.writeBoolean(v); }
        public Boolean read(FriendlyByteBuf b) { return b.readBoolean(); }
        public Tag toNBT(Boolean v) { return ByteTag.valueOf(v); }
        public Boolean fromNBT(Tag t) { return ((ByteTag) t).getAsByte() = 0; }
    };

    public static final DataSerializer<Integer> INT = new DataSerializer<>() {
        public void write(FriendlyByteBuf b, Integer v) { b.writeVarInt(v); }
        public Integer read(FriendlyByteBuf b) { return b.readVarInt(); }
        public Tag toNBT(Integer v) { return IntTag.valueOf(v); }
        public Integer fromNBT(Tag t) { return ((IntTag) t).getAsInt(); }
    };

    public static final DataSerializer<Float> FLOAT = new DataSerializer<>() {
        public void write(FriendlyByteBuf b, Float v) { b.writeFloat(v); }
        public Float read(FriendlyByteBuf b) { return b.readFloat(); }
        public Tag toNBT(Float v) { return FloatTag.valueOf(v); }
        public Float fromNBT(Tag t) { return ((FloatTag) t).getAsFloat(); }
    };

    public static final DataSerializer<String> STRING = new DataSerializer<>() {
        public void write(FriendlyByteBuf b, String v) { b.writeUtf(v); }
        public String read(FriendlyByteBuf b) { return b.readUtf(); }
        public Tag toNBT(String v) { return StringTag.valueOf(v); }
        public String fromNBT(Tag t) { return t.getAsString(); }
    };
}
