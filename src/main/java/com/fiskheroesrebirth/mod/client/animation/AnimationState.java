package com.fiskheroesrebirth.mod.client.animation;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rastreador de estado de animacoes por player
 * Thread-safe e otimizado
 */
public class AnimationState {

    private static final Map<UUID, PlayerAnimationData> STATES = new ConcurrentHashMap<>();

    public static PlayerAnimationData get(UUID playerId) {
        return STATES.computeIfAbsent(playerId, k -> new PlayerAnimationData());
    }

    public static void play(UUID playerId, String animation) {
        PlayerAnimationData data = get(playerId);
        data.currentAnimation = animation;
        data.startTime = System.currentTimeMillis();
        data.progress = 0f;
        data.playing = true;
    }

    public static void stop(UUID playerId) {
        PlayerAnimationData data = get(playerId);
        data.playing = false;
        data.currentAnimation = null;
    }

    public static String getCurrentAnimation(UUID playerId) {
        return get(playerId).currentAnimation;
    }

    public static float getProgress(UUID playerId) {
        return get(playerId).progress;
    }

    public static boolean isPlaying(UUID playerId) {
        return get(playerId).playing;
    }

    public static void remove(UUID playerId) {
        STATES.remove(playerId);
    }

    public static int activeCount() { return STATES.size(); }

    public static class PlayerAnimationData {
        public String currentAnimation = null;
        public long startTime = 0;
        public float progress = 0f;
        public boolean playing = false;
        public boolean loop = false;
        public int duration = 20;
        public float speed = 1.0f;
    }
}
