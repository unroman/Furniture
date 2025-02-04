package com.berksire.furniture.mixin;

import com.berksire.furniture.registry.ObjectRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LoomMenu.class)
public class LoomMenuMixin {
    @Shadow @Final private ContainerLevelAccess access;

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void stillValid(Player player, CallbackInfoReturnable<Boolean> cir) {
        boolean isValid = this.access.evaluate((world, pos) ->
                world.getBlockState(pos).is(ObjectRegistry.SEWING_KIT.get()) ||
                        world.getBlockState(pos).is(Blocks.LOOM), true);
        cir.setReturnValue(isValid);

    }
}
