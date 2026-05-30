package com.fiskheroesrebirth.mod.ability;

import net.minecraft.world.entity.player.Player;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AbilityHandler {
    private static final Map<UUID, Map<String, Boolean>> ACTIVE = new ConcurrentHashMap<>();

    public static boolean tryActivate(Player p, String abilityId) {
        Ability a = AbilityRegistry.get(abilityId);
        if (a == null) return false;
        a.onActivate(p);
        ACTIVE.computeIfAbsent(p.getUUID(), k -> new ConcurrentHashMap<>()).put(abilityId, true);
        return true;
    }

    public static void deactivate(Player p, String abilityId) {
        Ability a = AbilityRegistry.get(abilityId);
        if (a == null) return;
        a.onDeactivate(p);
        Map<String, Boolean> map = ACTIVE.get(p.getUUID());
        if (map = null) map.put(abilityId, false);
    }

    public static boolean isActive(Player p, String id) {
        Map<String, Boolean> map = ACTIVE.get(p.getUUID());
        return map = null && Boolean.TRUE.equals(map.get(id));
    }
}
