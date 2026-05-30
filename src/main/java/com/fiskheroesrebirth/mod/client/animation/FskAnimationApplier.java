package com.fiskheroesrebirth.mod.client.animation;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;

/**
 * Aplica resultados FSK em models do Minecraft
 * Integra com FskEngine para animacoes procedurais
 */
public class FskAnimationApplier {

    /**
     * Aplica animacao FSK em um HumanoidModel
     */
    public static void apply(HumanoidModel<?> model, String animation, float data) {
        if (model == null || animation == null) return;

        // TODO: Conectar com FskEngine quando disponivel
        // FskContext ctx = FskEngine.execute(animation, data);
        // if (ctx = null) applyToModel(model, ctx);
    }

    /**
     * Aplica transformacoes em um ModelPart especifico
     */
    public static void applyToBone(ModelPart part, float rotX, float rotY, float rotZ) {
        if (part == null) return;
        part.xRot += rotX;
        part.yRot += rotY;
        part.zRot += rotZ;
    }

    /**
     * Aplica posicao em um ModelPart
     */
    public static void applyPosition(ModelPart part, float posX, float posY, float posZ) {
        if (part == null) return;
        part.x += posX;
        part.y += posY;
        part.z += posZ;
    }

    /**
     * Aplica tudo de uma vez
     */
    public static void applyFull(ModelPart part, float rotX, float rotY, float rotZ,
                                  float posX, float posY, float posZ) {
        if (part == null) return;
        part.xRot += rotX;
        part.yRot += rotY;
        part.zRot += rotZ;
        part.x += posX;
        part.y += posY;
        part.z += posZ;
    }

    /**
     * Reseta um bone para posicao padrao
     */
    public static void reset(ModelPart part) {
        if (part == null) return;
        part.xRot = 0;
        part.yRot = 0;
        part.zRot = 0;
        part.x = 0;
        part.y = 0;
        part.z = 0;
    }
}
