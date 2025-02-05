package me.codexadrian.spirit.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.codexadrian.spirit.blocks.blockentity.SoulCageBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.jetbrains.annotations.NotNull;

public class SoulCageRenderer implements BlockEntityRenderer<SoulCageBlockEntity> {

    public SoulCageRenderer(BlockEntityRendererProvider.Context context) {
        super();
    }

    @Override
    public void render(SoulCageBlockEntity blockEntity, float f, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource multiBufferSource, int i, int j) {
        if (!blockEntity.hasLevel() || blockEntity.type == null) return;
        matrixStack.pushPose();
        matrixStack.translate(0.5D, 0.0D, 0.5D);
        var entity = blockEntity.getOrCreateEntity();

        float g = 0.53125F;
        float h = Math.max(entity.getBbWidth(), entity.getBbHeight());
        if ((double) h > 1.0D) {
            g /= h;
        }

        matrixStack.translate(0.0D, 0.4000000059604645D, 0.0D);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees((float) blockEntity.getSpawner().getSpin()));
        matrixStack.translate(0.0D, -0.20000000298023224D, 0.0D);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(-30.0F));
        matrixStack.scale(g, g, g);
        Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0.0D, 0.0D, 0.0D, 0.0F, f, matrixStack, multiBufferSource, i);
        matrixStack.popPose();
    }
}
