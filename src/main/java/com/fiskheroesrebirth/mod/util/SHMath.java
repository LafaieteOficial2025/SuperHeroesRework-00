package com.fiskheroesrebirth.mod.util;

public class SHMath {
    public static final float PI = (float) Math.PI;
    public static final float TAU = PI * 2;

    public static float toRad(float deg) { return deg * PI / 180f; }
    public static float toDeg(float rad) { return rad * 180f / PI; }
    public static float lerp(float a, float b, float t) { return a + (b - a) * t; }
    public static float clamp(float v, float min, float max) { return Math.max(min, Math.min(max, v)); }

    public static float smoothstep(float a, float b, float t) {
        t = clamp((t - a) / (b - a), 0, 1);
        return t * t * (3 - 2 * t);
    }
}
