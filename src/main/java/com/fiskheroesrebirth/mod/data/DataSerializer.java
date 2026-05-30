package com.fiskheroesrebirth.mod.data;

import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;

public interface DataSerializer<T> {
    void write(FriendlyByteBuf buf, T value);
    T read(FriendlyByteBuf buf);
    Tag toNBT(T value);
    T fromNBT(Tag tag);
}
