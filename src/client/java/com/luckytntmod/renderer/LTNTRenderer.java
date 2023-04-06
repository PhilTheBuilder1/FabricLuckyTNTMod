package com.luckytntmod.renderer;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.entity.LTNTEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;


public class LTNTRenderer extends EntityRenderer<Entity> {
    private BlockRenderManager blockRender;

    private static final String NAMESPACE = LuckyTNTMod.NAMESPACE;
    public LTNTRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.blockRender = ctx.getBlockRenderManager();
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return null;
    }
    @Override
    public void render(Entity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0F, 0.5F, 0.0F);
        LTNTEntity ltntEntity = (LTNTEntity) entity;
        int j = ltntEntity.getFuse();
        if ((float)j - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float)j - g + 1.0F) / 10.0F;
            h = MathHelper.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float k = 1.0F + h * 0.3F;
            matrixStack.scale(k, k, k);
        }

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
        matrixStack.translate(-0.5F, -0.5F, 0.5F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
        TntMinecartEntityRenderer.renderFlashingBlock(this.blockRender, ltntEntity.block.getDefaultState(), matrixStack, vertexConsumerProvider, i, j / 5 % 2 == 0);
        matrixStack.pop();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    /*@Override
    public Identifier getTexture(Entity entity) {
        return new Identifier(NAMESPACE, "test");
    }*/

}
