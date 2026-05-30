package com.fiskheroesrebirth.mod.energy;

import net.minecraft.world.entity.player.Player;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownManager {
    private static final Map<UUID, Map<String, Integer>> COOLDOWNS = new ConcurrentHashMap<>();

    public static void set(Player p, String id, int ticks) {
        COOLDOWNS.computeIfAbsent(p.getUUID(), k -> new ConcurrentHashMap<>()).put(id, ticks);
    }

    public static int get(Player p, String id) {
        Map<String, Integer> m = COOLDOWNS.get(p.getUUID());
        return m == null ? 0 : m.getOrDefault(id, 0);
    }

    public static boolean isReady(Player p, String id) { return get(p, id) <= 0; }

    public static void tick(Player p) {
        Map<String, Integer> m = COOLDOWNS.get(p.getUUID());
        if (m == null) return;
        m.replaceAll((k, v) -> Math.max(0, v - 1));
    }
}
