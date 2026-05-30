package com.fiskheroesrebirth.mod.client.animation;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registro de animacoes mapeadas por ID
 * Mapeia keys de abilities para animacoes .fsk
 */
public class AnimationRegistry {

    private static final Map<String, AnimationData> REGISTRY = new ConcurrentHashMap<>();

    public static void register(String id, String fskFile, int duration, boolean loop) {
        REGISTRY.put(id, new AnimationData(id, fskFile, duration, loop));
    }

    public static AnimationData get(String id) {
        return REGISTRY.get(id);
    }

    public static Collection<AnimationData> getAll() {
        return REGISTRY.values();
    }

    public static int count() { return REGISTRY.size(); }

    public static void registerDefaults() {
        // Iron Man
        register("helmet_deploy", "mk46_helmet", 20, false);
        register("repulsor_fire", "dual_hand_beam", 15, false);
        register("flight_lean", "flight_lean", 20, true);
        register("iron_dive", "iron_man_dive", 30, false);

        // Spider-Man
        register("web_shoot_left", "web_shoot_left", 10, false);
        register("web_shoot_right", "web_shoot_right", 10, false);
        register("swing_default", "swing_default", 30, true);
        register("swing_dive", "swing_dive", 20, false);
        register("wall_crawl", "crawl_wall", 20, true);
        register("ceiling_crawl", "crawl_ceiling", 20, true);

        // Flash
        register("speedster_sprint", "speedster_sprint", 10, true);

        // Combat
        register("blocking", "blocking", 15, true);
        register("blocking_arm", "blocking_arm", 15, true);
        register("ocular_beam", "ocular_beam", 20, false);
        register("sword_pose", "sword_pose", 20, true);

        // Movement
        register("hero_landing", "superhero_landing", 25, false);
        register("falcon_dive", "falcon_dive_roll", 30, false);
        register("manta_flight", "mantapack_flight", 30, true);

        // Aiming
        register("aiming", "aiming", 10, true);
        register("aiming_left", "aiming_left", 10, true);
        register("dual_aiming", "dual_aiming", 10, true);

        // Web aim
        register("web_aim_left", "web_aim_left", 10, true);
        register("web_aim_right", "web_aim_right", 10, true);
        register("web_rappel", "web_rappel", 25, false);

        // Gestures
        register("hat_tip", "hat_tip_player", 15, false);
        register("remove_cowl", "remove_cowl", 20, false);

        System.out.println("[Animations] " + REGISTRY.size() + " animacoes registradas");
    }

    public static class AnimationData {
        public final String id;
        public final String fskFile;
        public final int duration;
        public final boolean loop;

        public AnimationData(String id, String fsk, int dur, boolean loop) {
            this.id = id;
            this.fskFile = fsk;
            this.duration = dur;
            this.loop = loop;
        }
    }
}
