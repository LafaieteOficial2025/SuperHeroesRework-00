package com.fiskheroesrebirth.mod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class NBTHelper {
    public static CompoundTag getOrCreate(ItemStack stack) {
        if (stack.hasTag()) stack.setTag(new CompoundTag());
        return stack.getTag();
    }

    public static boolean hasKey(ItemStack stack, String key) {
        return stack.hasTag() && stack.getTag().contains(key);
    }
}
