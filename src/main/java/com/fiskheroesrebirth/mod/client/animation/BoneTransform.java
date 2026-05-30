package com.fiskheroesrebirth.mod.client.animation;

/**
 * Representa uma transformacao a ser aplicada em um bone
 * Imutavel para thread-safety
 */
public class BoneTransform {

    public final String boneName;
    public final float rotX, rotY, rotZ;
    public final float posX, posY, posZ;
    public final float scaleX, scaleY, scaleZ;

    public BoneTransform(String boneName,
                         float rotX, float rotY, float rotZ,
                         float posX, float posY, float posZ) {
        this(boneName, rotX, rotY, rotZ, posX, posY, posZ, 1f, 1f, 1f);
    }

    public BoneTransform(String boneName,
                         float rotX, float rotY, float rotZ,
                         float posX, float posY, float posZ,
                         float scaleX, float scaleY, float scaleZ) {
        this.boneName = boneName;
        this.rotX = rotX; this.rotY = rotY; this.rotZ = rotZ;
        this.posX = posX; this.posY = posY; this.posZ = posZ;
        this.scaleX = scaleX; this.scaleY = scaleY; this.scaleZ = scaleZ;
    }

    public static BoneTransform identity(String boneName) {
        return new BoneTransform(boneName, 0, 0, 0, 0, 0, 0);
    }

    public BoneTransform combine(BoneTransform other) {
        return new BoneTransform(boneName,
            rotX + other.rotX, rotY + other.rotY, rotZ + other.rotZ,
            posX + other.posX, posY + other.posY, posZ + other.posZ,
            scaleX * other.scaleX, scaleY * other.scaleY, scaleZ * other.scaleZ
        );
    }

    public BoneTransform scale(float factor) {
        return new BoneTransform(boneName,
            rotX * factor, rotY * factor, rotZ * factor,
            posX * factor, posY * factor, posZ * factor,
            scaleX, scaleY, scaleZ
        );
    }

    @Override
    public String toString() {
        return String.format("BoneTransform[%s rot=(%.2f,%.2f,%.2f) pos=(%.2f,%.2f,%.2f)]",
            boneName, rotX, rotY, rotZ, posX, posY, posZ);
    }
}
