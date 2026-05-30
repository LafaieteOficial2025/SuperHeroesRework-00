package com.fiskheroesrebirth.mod.ability;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AbilityRegistry {
    private static final Map<String, Ability> REGISTRY = new ConcurrentHashMap<>();

    public static void register(Ability a) { REGISTRY.put(a.id, a); }
    public static Ability get(String id) { return REGISTRY.get(id); }
    public static Collection<Ability> getAll() { return REGISTRY.values(); }

    public static void registerAll() {
        System.out.println("[Abilities] Sistema inicializado");
    }
}
