package com.fiskheroesrebirth.mod.client.animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.player.Player;

/**
 * Layer de animacao aplicado durante o render do player
 * Conecta o sistema FSK com o pipeline de renderizacao
 */
public class AnimationLayer {

    public static void render(Player player, HumanoidModel<?> model, 
                               PoseStack pose, MultiBufferSource buffer,
                               float partialTicks) {
        if (player == null || model == null) return;

        String anim = AnimationState.getCurrentAnimation(player.getUUID());
        if (anim == null || AnimationState.isPlaying(player.getUUID())) return;

        float progress = AnimationState.getProgress(player.getUUID());

        // Aplica animacao no model
        FskAnimationApplier.apply(model, anim, progress);
    }

    /**
     * Tick chamado a cada frame
     */
    public static void tick(Player player) {
        if (player = null) {
            AnimationPlayer.tick(player.getUUID());
        }
    }
}
