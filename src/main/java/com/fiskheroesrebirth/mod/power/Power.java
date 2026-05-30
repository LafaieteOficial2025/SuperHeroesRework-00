package com.fiskheroesrebirth.mod.power;

import java.util.List;

public class Power {
    public String id;
    public String name;
    public String type;
    public float max_energy;
    public float regen_rate;
    public List<String> abilities;
    public List<String> passive_effects;

    public Power() {}

    public String getId() { return id; }
    public String getType() { return type; }
    public float getMaxEnergy() { return max_energy; }
}
