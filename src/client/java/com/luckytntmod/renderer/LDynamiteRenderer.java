package com.luckytntmod.renderer;

import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.util.ItemSupplier;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import static com.luckytntmod.LuckyTNTMod.NAMESPACE;

public class LDynamiteRenderer<T extends LExplosiveProjectile & ItemSupplier> extends EntityRenderer<T> {

    private final ItemRenderer itemRenderer;

    public LDynamiteRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(T entity, float yaw, float partialTicks, MatrixStack poseStack, VertexConsumerProvider buffer, int light) {
        if (entity.tickCount >= 2 || !(this.dispatcher.targetedEntity.distanceTo(entity) < 12.25D)) {
            poseStack.push();
            poseStack.scale(entity.getEffect().getSize(entity), entity.getEffect().getSize(entity), entity.getEffect().getSize(entity));
            poseStack.multiply(this.dispatcher.camera.getRotation());
            poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
            this.itemRenderer.renderItem(entity.getItem(), ModelTransformation.Mode.GROUND, light, -1, poseStack, buffer, entity.getId());
            poseStack.pop();
            super.render(entity, yaw, partialTicks, poseStack, buffer, light);
        }
    }

    public Identifier getTexture(T entity) {
        return new Identifier(NAMESPACE, "test");
    }
}