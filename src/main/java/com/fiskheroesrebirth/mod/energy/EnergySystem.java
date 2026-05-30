package com.fiskheroesrebirth.mod.energy;

import net.minecraft.world.entity.player.Player;
import com.fiskheroesrebirth.mod.data.SHData;
import com.fiskheroesrebirth.mod.data.SHDataManager;

public class EnergySystem {
    public static boolean consume(Player p, float amount) {
        float current = SHDataManager.getValue(p, SHData.ENERGY);
        if (current < amount) return false;
        SHDataManager.setValue(p, SHData.ENERGY, current - amount);
        return true;
    }

    public static void regenerate(Player p, float amount) {
        float current = SHDataManager.getValue(p, SHData.ENERGY);
        float max = SHDataManager.getValue(p, SHData.MAX_ENERGY);
        SHDataManager.setValue(p, SHData.ENERGY, Math.min(max, current + amount));
    }

    public static float getEnergy(Player p) {
        return SHDataManager.getValue(p, SHData.ENERGY);
    }

    public static float getMaxEnergy(Player p) {
        return SHDataManager.getValue(p, SHData.MAX_ENERGY);
    }
}
