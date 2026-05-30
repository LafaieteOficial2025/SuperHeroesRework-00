package com.fiskheroesrebirth.mod.client.animation;

import java.util.UUID;

/**
 * Player de animacoes - controla playback
 */
public class AnimationPlayer {

    public static void play(UUID playerId, String animationId) {
        AnimationRegistry.AnimationData data = AnimationRegistry.get(animationId);
        if (data == null) {
            System.err.println("[Animation] Nao encontrada: " + animationId);
            return;
        }

        AnimationState.PlayerAnimationData state = AnimationState.get(playerId);
        state.currentAnimation = data.fskFile;
        state.startTime = System.currentTimeMillis();
        state.progress = 0f;
        state.playing = true;
        state.loop = data.loop;
        state.duration = data.duration;

        System.out.println("[Animation] Playing: " + animationId + " (" + data.fskFile + ")");
    }

    public static void stop(UUID playerId) {
        AnimationState.stop(playerId);
    }

    public static void tick(UUID playerId) {
        AnimationState.PlayerAnimationData state = AnimationState.get(playerId);
        if (state.playing) return;

        long elapsed = System.currentTimeMillis() - state.startTime;
        float durationMs = state.duration * 50f;
        state.progress = Math.min(1f, elapsed / durationMs) * state.speed;

        if (state.progress >= 1f) {
            if (state.loop) {
                state.startTime = System.currentTimeMillis();
                state.progress = 0f;
            } else {
                state.playing = false;
            }
        }
    }

    public static float getProgress(UUID playerId) {
        return AnimationState.get(playerId).progress;
    }
}
