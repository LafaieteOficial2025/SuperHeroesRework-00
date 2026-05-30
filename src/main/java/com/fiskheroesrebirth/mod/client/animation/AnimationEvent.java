package com.fiskheroesrebirth.mod.client.animation;

import java.util.UUID;

/**
 * Evento disparado durante animacoes
 * Permite reagir a momentos especificos (ex: tocar som em frame X)
 */
public class AnimationEvent {

    public enum Type {
        START,      // Animacao comecou
        END,        // Animacao terminou
        LOOP,       // Animacao fez loop
        KEYFRAME,   // Atingiu um keyframe especifico
        INTERRUPT;  // Animacao foi interrompida
    }

    public final Type type;
    public final UUID playerId;
    public final String animationId;
    public final float progress;
    public final long timestamp;

    public AnimationEvent(Type type, UUID playerId, String animationId, float progress) {
        this.type = type;
        this.playerId = playerId;
        this.animationId = animationId;
        this.progress = progress;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.format("[AnimEvent] %s: %s @ %.2f", type, animationId, progress);
    }
}
