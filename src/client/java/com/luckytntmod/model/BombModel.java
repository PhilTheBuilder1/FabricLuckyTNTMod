package com.luckytntmod.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class BombModel <T extends Entity> extends EntityModel<T> {
    //public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(LuckyTNTMod.NAMESPACE, "tsa_bomb_model"), "main");
    private final ModelPart TsarBomb;

    public BombModel() {
        this.TsarBomb = getTexturedModelData().createModel().getChild("TsarBomb");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData partDefinition = data.getRoot();

        partDefinition.addChild("TsarBomb", ModelPartBuilder.create().uv(76, 26).cuboid(14.8344F, -9.9988F, -0.998F, 5.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(76, 16).cuboid(14.8344F, 4.0012F, -0.998F, 5.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(20, 67).cuboid(14.8344F, -0.9988F, 4.002F, 5.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(60, 62).cuboid(14.8344F, -0.9988F, -9.998F, 5.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 36).cuboid(8.0844F, -2.9988F, -2.998F, 11.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(36, 18).cuboid(2.0844F, -3.9988F, -3.998F, 6.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-17.9156F, -3.9988F, -3.998F, 20.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(56, 52).cuboid(2.0844F, -4.9988F, -3.998F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 26).cuboid(-11.9156F, -5.9988F, -3.998F, 14.0F, 2.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-11.9156F, 4.0012F, -3.998F, 14.0F, 2.0F, 8.0F, new Dilation(0.0F))
                .uv(4, 21).cuboid(8.0844F, -3.9988F, 3.002F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 21).cuboid(8.0844F, -3.9988F, -3.998F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 5).cuboid(8.0844F, 3.0012F, -3.998F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 5).cuboid(8.0844F, 3.0012F, 3.002F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 26).cuboid(2.0844F, 4.0012F, -3.998F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(28, 43).cuboid(8.0844F, -3.9988F, -2.998F, 12.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(34, 36).cuboid(8.0844F, 3.0012F, -2.998F, 12.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(64, 35).cuboid(8.0844F, -2.9988F, -3.998F, 12.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 61).cuboid(8.0844F, -2.9988F, 3.002F, 12.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 76).cuboid(2.0844F, -3.9988F, 4.002F, 6.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(19, 75).cuboid(2.0844F, -3.9988F, -4.998F, 6.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 8).cuboid(-17.9156F, -4.9988F, -3.998F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 60).cuboid(-19.9156F, -3.9988F, -3.998F, 2.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(33, 75).cuboid(-21.9156F, -1.9988F, -1.998F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(76, 52).cuboid(-22.9156F, -1.9988F, -1.998F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(71, 0).cuboid(-21.9156F, -2.9988F, -1.998F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(28, 36).cuboid(-21.9156F, 2.0012F, -1.998F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-21.9156F, -1.9988F, -2.998F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-21.9156F, -1.9988F, 2.002F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(68, 7).cuboid(-17.9156F, -3.9988F, -4.998F, 6.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(70, 70).cuboid(-17.9156F, -3.9988F, 4.002F, 6.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 17).cuboid(-17.9156F, 4.0012F, -3.998F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(32, 50).cuboid(-11.9156F, -3.9988F, -5.998F, 14.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 50).cuboid(-11.9156F, -3.9988F, 4.002F, 14.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(62, 50).cuboid(-11.9156F, -4.9988F, 4.002F, 14.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(58, 47).cuboid(-11.9156F, -4.9988F, -4.998F, 14.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(58, 45).cuboid(-11.9156F, 4.0012F, -4.998F, 14.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(58, 43).cuboid(-11.9156F, 4.0012F, 4.002F, 14.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.0012F, 4.0844F, -0.002F, 0.0F, 0.0F, 1.5708F));
        return TexturedModelData.of(data, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        TsarBomb.render(matrices, vertices, light, overlay);
    }
}