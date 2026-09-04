package mods.Hileb.respawn.mixin;

import mods.Hileb.respawn.CommonClass;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 1.12.2 counterpart of the upstream {@code ServerPlayer.die} inject.
 * In 1.12.2 the player death handler is {@code EntityPlayerMP.onDeath}.
 */
@Mixin(EntityPlayerMP.class)
public class PlayerMPMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void rs$onDeath(DamageSource source, CallbackInfo ci) {
        CommonClass.onDeath((EntityPlayerMP) (Object) this);
    }
}
