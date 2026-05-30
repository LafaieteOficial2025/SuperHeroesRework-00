package com.fiskheroesrebirth.mod.ability;

import net.minecraft.world.entity.player.Player;

public abstract class Ability {
    public final String id;
    public final String name;
    public float energyCost = 10f;
    public int cooldownTicks = 20;
    public String animation = "";

    public Ability(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void onActivate(Player player);
    public abstract void onDeactivate(Player player);
    public void onTick(Player player) {}

    public Ability energy(float c) { this.energyCost = c; return this; }
    public Ability cooldown(int t) { this.cooldownTicks = t; return this; }
    public Ability animation(String a) { this.animation = a; return this; }
}
