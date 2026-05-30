package com.fiskheroesrebirth.mod.suit;

public class Suit {
    public String id;
    public String hero;
    public String name;
    public String texture;
    public String model;
    public int armor_value;
    public float boost_speed;
    public float boost_jump;
    public boolean first_person_arms;
    public boolean glow_effect;
    public boolean flight_capable;
    public boolean wall_crawl;
    public String helmet_animation;

    public Suit() {}

    public String getId() { return id; }
    public String getTexture() { return texture; }
}
