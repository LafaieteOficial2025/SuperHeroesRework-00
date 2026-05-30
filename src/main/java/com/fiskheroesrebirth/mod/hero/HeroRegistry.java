package com.fiskheroesrebirth.mod.hero;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HeroRegistry {
    private static final Map<String, Hero> HEROES = new ConcurrentHashMap<>();

    public static void register(Hero hero) {
        HEROES.put(hero.id, hero);
    }

    public static Hero get(String id) { return HEROES.get(id); }
    public static Collection<Hero> getAll() { return HEROES.values(); }
    public static int count() { return HEROES.size(); }

    public static void loadAll() {
        System.out.println("[Heroes] Sistema inicializado");
    }
}
