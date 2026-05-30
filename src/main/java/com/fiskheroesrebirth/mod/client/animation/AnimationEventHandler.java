package com.fiskheroesrebirth.mod.client.animation;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Handler de eventos de animacao
 * Permite registrar listeners para eventos especificos
 */
public class AnimationEventHandler {

    private static final List<Consumer<AnimationEvent>> LISTENERS = new CopyOnWriteArrayList<>();

    public static void register(Consumer<AnimationEvent> listener) {
        LISTENERS.add(listener);
    }

    public static void unregister(Consumer<AnimationEvent> listener) {
        LISTENERS.remove(listener);
    }

    public static void fire(AnimationEvent event) {
        for (Consumer<AnimationEvent> listener : LISTENERS) {
            try {
                listener.accept(event);
            } catch (Exception e) {
                System.err.println("[AnimEvent] Erro em listener: " + e.getMessage());
            }
        }
    }

    public static int listenerCount() { return LISTENERS.size(); }
}
