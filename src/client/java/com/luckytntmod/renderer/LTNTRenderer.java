package com.luckytntmod.renderer;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.util.IExplosiveEntity;
import net.minecraft.block.TntBlock;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.joml.Math;


public class LTNTRenderer extends EntityRenderer<Entity> {
    private final BlockRenderManager blockRenderer;

    private static final String NAMESPACE = LuckyTNTMod.NAMESPACE;
    public LTNTRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.blockRenderer = ctx.getBlockRenderManager();
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return null;
    }
    @Override
    public void render(Entity entity, float yaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
        if(entity instanceof IExplosiveEntity ent) {
            matrixStack.push();
            matrixStack.translate(0, 0, 0);
            int i = ent.getTNTFuse();
            if ((float) i - partialTicks + 1.0F < 10.0F && ent.getEffect().getBlockState((IExplosiveEntity) entity).getBlock() instanceof TntBlock) {
                float f = 1.0F - ((float)i - partialTicks + 1.0F) / 10.0F;
                f = Math.clamp(f, 0.0F, 1.0F);
                f *= f;
                f *= f;
                float f1 = 1.0F + f * 0.3F;
                matrixStack.scale(f1, f1, f1);
            }
            matrixStack.scale(ent.getEffect().getSize((IExplosiveEntity)entity), ent.getEffect().getSize((IExplosiveEntity)entity), ent.getEffect().getSize((IExplosiveEntity)entity));
            matrixStack.translate(-0.5d, 0, -0.5d);
            TntMinecartEntityRenderer.renderFlashingBlock(blockRenderer, ent.getBlock().getDefaultState(), matrixStack, vertexConsumerProvider, light, ent.getBlock() instanceof TntBlock && i / 5 % 2 == 0);
            matrixStack.pop();
        }
        super.render(entity, yaw, partialTicks, matrixStack, vertexConsumerProvider, light);
    }

    /*@Override
    public Identifier getTexture(Entity entity) {
        return new Identifier(NAMESPACE, "test");
    }*/

}
