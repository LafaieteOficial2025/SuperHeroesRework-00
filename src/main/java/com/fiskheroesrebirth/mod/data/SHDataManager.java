package com.fiskheroesrebirth.mod.data;

import net.minecraft.world.entity.player.Player;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SHDataManager {
    private static final Map<UUID, DataHolder> HOLDERS = new ConcurrentHashMap<>();

    public static DataHolder get(Player player) {
        return HOLDERS.computeIfAbsent(player.getUUID(), k -> new DataHolder());
    }

    public static <T> T getValue(Player player, DataKey<T> key) {
        return get(player).get(key);
    }

    public static <T> void setValue(Player player, DataKey<T> key, T value) {
        get(player).set(key, value);
    }

    public static void remove(UUID id) { HOLDERS.remove(id); }
}
