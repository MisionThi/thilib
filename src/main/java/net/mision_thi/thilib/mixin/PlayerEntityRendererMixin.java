package net.mision_thi.thilib.mixin;


import io.github.apace100.apoli.component.PowerHolderComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.mision_thi.thilib.ThiLib;
import net.mision_thi.thilib.mixin.accessor.WolfEntityModelAccessor;
import net.mision_thi.thilib.powers.MobModelPower;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity,
        PlayerEntityModel<AbstractClientPlayerEntity>> {

    @Shadow public abstract void renderLeftArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player);

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx,
                                     PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Redirect( //Starts the render redirect - I assume it makes the game execute the following method instead of the target
            method="render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")
    )

    private void renderWolf(LivingEntityRenderer renderer,
                            LivingEntity entity, float f, float g, MatrixStack matrixStack,
                            VertexConsumerProvider vertexConsumerProvider, int i){

        if(PowerHolderComponent.hasPower(entity, MobModelPower.class)) {
            MobModelPower power = PowerHolderComponent.getPowers(entity, MobModelPower.class).get(0);

            if(power.isActive()) {
                //public Entity modelEntity = power.modelEntity;
                LivingEntity modelEntity = power.modelEntity;
                //I believe this chunk sets the physical aspects of the modelEntity to those of the player
                modelEntity.lastLimbDistance = entity.lastLimbDistance;
                modelEntity.limbDistance = entity.limbDistance;
                modelEntity.limbAngle = entity.limbAngle;
                modelEntity.handSwinging = entity.handSwinging;
                modelEntity.handSwingTicks = entity.handSwingTicks;
                modelEntity.lastHandSwingProgress = entity.lastHandSwingProgress;
                modelEntity.handSwingProgress = entity.handSwingProgress;
                modelEntity.bodyYaw = entity.bodyYaw;
                modelEntity.prevBodyYaw = entity.prevBodyYaw;
                modelEntity.headYaw = entity.headYaw;
                modelEntity.prevHeadYaw = entity.prevHeadYaw;
                modelEntity.age = entity.age;
                modelEntity.preferredHand = entity.preferredHand;
                modelEntity.setOnGround(entity.isOnGround());
                modelEntity.setVelocity(entity.getVelocity());
                //next two make the modelEntity tamed (with the player themselves as the owner),
                //and their collar colour to light gray (which is harder to see on the wolf model)
                //modelEntity.setOwner((PlayerEntity) entity);
                //modelEntity.setCollarColor(DyeColor.LIGHT_GRAY);
                //These next two make the wolf use the angry wolf texture
                //modelEntity.setAngerTime(100);
                //modelEntity.setAngryAt(entity.getUuid());

                // Old
                //WolfEntityRenderer entityRenderer = (WolfEntityRenderer) MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(modelEntity);
                //entityRenderer.render(modelEntity, f, g, matrixStack, vertexConsumerProvider, i);

                EntityRenderer modelRenderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(modelEntity);
                modelRenderer.render(modelEntity, f, g, matrixStack, vertexConsumerProvider, i);

            } else{
                super.render((AbstractClientPlayerEntity) entity, f, g, matrixStack, vertexConsumerProvider, i);
            }
        } else{
            super.render((AbstractClientPlayerEntity) entity, f, g, matrixStack, vertexConsumerProvider, i);
        }

    }

    @Inject(method = "renderArm", at = @At("HEAD"), cancellable = true)
    private void renderPlayerArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        if (PowerHolderComponent.hasPower(player, MobModelPower.class)) {
            MobModelPower power = PowerHolderComponent.getPowers(player, MobModelPower.class).get(0);

            // Check if the power is active & check if the entity isn't null.
            if (power.isActive() & power.modelEntity != null) {
                // Hardcoded
//                PlayerEntityModel playerEntityModel = (PlayerEntityModel) this.getModel();
//                ((PlayerEntityRenderer) (Object) this).setModelPose(player);
//                playerEntityModel.handSwingProgress = 0.0F;
//                playerEntityModel.sneaking = false;
//                playerEntityModel.leaningPitch = 0.0F;
//                playerEntityModel.setAngles(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
//                arm.pitch = 0.0F;
//                arm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(ThiLib.identifier("textures/entity/wolf.png"))), light, OverlayTexture.DEFAULT_UV);
//                sleeve.pitch = 0.0F;
//                sleeve.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(ThiLib.identifier("textures/entity/wolf.png"))), light, OverlayTexture.DEFAULT_UV);
//                ci.cancel();

//                //// Try
//                PlayerEntityModel playerEntityModel = (PlayerEntityModel) this.getModel();
//                ((PlayerEntityRenderer) (Object) this).setModelPose(player);
//                playerEntityModel.handSwingProgress = 0.0F;
//                playerEntityModel.sneaking = false;
//                playerEntityModel.leaningPitch = 0.0F;
//                playerEntityModel.setAngles(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
//                arm.pitch = 0.0F;
//                arm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(ThiLib.identifier("textures/entity/wolf.png"))),light, OverlayTexture.DEFAULT_UV);
//                arm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(ThiLib.identifier("textures/entity/wolf.png"))),light,OverlayTexture.DEFAULT_UV );
//
//                sleeve.pitch = 0.0F;
//                sleeve.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(ThiLib.identifier("textures/entity/wolf.png"))), light, OverlayTexture.DEFAULT_UV);
//                ci.cancel();

//              //Old try
                LivingEntity modelEntity = power.modelEntity;
                LivingEntityRenderer entityRenderer = (LivingEntityRenderer) MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(modelEntity);
                EntityModel modelEntityModel = entityRenderer.getModel();
                ((PlayerEntityRenderer) (Object) this).setModelPose(player);
                modelEntityModel.handSwingProgress = 0.0F;
                modelEntityModel.setAngles(modelEntity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
//                modelEntityModel.rightFrontLeg.pitch = 0.0F;
//                ((WolfEntityModelAccessor) modelEntityModel).getRightFrontLeg().pitch = 0.0F;
                double x = -0.28125;
                if (player.getActiveHand().equals(Hand.OFF_HAND)) x *= -1;
                matrices.translate(x, -0.75, 0.25);
//                modelEntity.rightFrontLeg.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(entityRenderer.getTexture(modelEntity))), light, OverlayTexture.DEFAULT_UV);
                ((WolfEntityModelAccessor) modelEntity).getRightFrontLeg().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(entityRenderer.getTexture(modelEntity))), light, OverlayTexture.DEFAULT_UV);
                ci.cancel();
//            }
            }
        }

//    @Inject(method = "renderRightArm", at = @At("HEAD"), cancellable = true)
//    private void renderRightArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, CallbackInfo ci) {
//        if(PowerHolderComponent.hasPower(player, MobModelPower.class)) {
//            MobModelPower power = PowerHolderComponent.getPowers(player, MobModelPower.class).get(0);
//
//            // Check if the power is active & check if the entity isn't null.
//            if (power.isActive() & power.modelEntity != null) {
//                matrices.translate(-0.5,-0.75,0.25);
//                matrices.scale(5, 5, 5);
//                renderPlayerArm(matrices, vertexConsumers, light, player, ((PlayerEntityModel)this.model).rightArm, ((PlayerEntityModel)this.model).rightSleeve, ci);
//
//                ci.cancel();
//            }
//        }
//    }
    }
}



    //Render arms of the player
//    @Inject(method = "renderArm", at = @At("HEAD"), cancellable = true)
//    private void renderPlayerArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
//        if(PowerHolderComponent.hasPower(player, MobModelPower.class)) {
//            MobModelPower power = PowerHolderComponent.getPowers(player, MobModelPower.class).get(0);
//
//            // Check if the power is active & check if the entity isn't null.
//            if (power.isActive() & power.modelEntity != null) {
//                EntityRenderer<?> renderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(power.modelEntity);
//
//                if (renderer instanceof LivingEntityRenderer) {
//                    LivingEntityRenderer<LivingEntity, ?> rendererCasted = (LivingEntityRenderer<LivingEntity, ?>) renderer;
//                    //PlayerEntityRenderer rendererCasted = (PlayerEntityRenderer) renderer;
//
//                    PlayerEntityModel<AbstractClientPlayerEntity> playerEntityModel = this.getModel();
//
//                    ((PlayerEntityRenderer) (Object) this).setModelPose(player);
//                    playerEntityModel.handSwingProgress = 0.0F;
//                    playerEntityModel.sneaking = false;
//                    playerEntityModel.leaningPitch = 0.0F;
//                    playerEntityModel.setAngles(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
//                    arm.pitch = 0.0F;
//                    //arm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(ThiLib.identifier("textures/entity/wolf.png"))), light, OverlayTexture.DEFAULT_UV);
//                    //arm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(Identifier.tryParse("thilib/textures/entity/wolf.png"))), light, OverlayTexture.DEFAULT_UV);
//                    //arm.render(matrices, vertexConsumers.getBuffer(((LivingEntityRendererAccessor) rendererCasted).callGetRenderLayer(power.modelEntity, true, false, true)), light, OverlayTexture.DEFAULT_UV);
//                    renderLeftArm();
//
//                    sleeve.pitch = 0.0F;
//                    sleeve.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(ThiLib.identifier("textures/entity/wolf.png"))), light, OverlayTexture.DEFAULT_UV);
//                    //sleeve.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(Identifier.tryParse("thilib/textures/entity/wolf.png"))), light, OverlayTexture.DEFAULT_UV);
//                    //sleeve.render(matrices, vertexConsumers.getBuffer(((LivingEntityRendererAccessor) rendererCasted).callGetRenderLayer(power.modelEntity, true, false, true)), light, OverlayTexture.DEFAULT_UV);
//                    ci.cancel();
//                }
//            }
//        }
//    }


