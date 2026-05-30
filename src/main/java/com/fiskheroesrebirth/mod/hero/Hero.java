package com.fiskheroesrebirth.mod.hero;

import java.util.*;

public class Hero {
    public final String id;
    public String name;
    public String description;
    public String texture;
    public final List<String> abilities = new ArrayList<>();
    public final Map<String, Float> attributes = new HashMap<>();
    public int color = 0xFFFFFF;
    public String alignment = "neutral";
    public String universe = "marvel";

    public Hero(String id) { this.id = id; }

    public Hero name(String n) { this.name = n; return this; }
    public Hero description(String d) { this.description = d; return this; }
    public Hero texture(String t) { this.texture = t; return this; }
    public Hero color(int c) { this.color = c; return this; }
    public Hero alignment(String a) { this.alignment = a; return this; }
    public Hero universe(String u) { this.universe = u; return this; }
    public Hero addAbility(String id) { abilities.add(id); return this; }
    public Hero addAttribute(String name, float value) { attributes.put(name, value); return this; }
}
