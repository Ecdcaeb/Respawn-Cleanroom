package mods.Hileb.respawn.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

/**
 * 1.12.2 counterpart of the upstream {@code findRespawnAndUseSpawnBlock} inject.
 * <p>
 * Upstream (1.21) overrides the respawn position returned by
 * {@code ServerPlayer.findRespawnAndUseSpawnBlock}. In 1.12.2 the respawn
 * position is computed in {@code PlayerList.recreatePlayerEntity} via the
 * single caller {@code EntityPlayer.getBedSpawnLocation}, so that is the
 * injection point here. It runs for death respawns, end-exit respawns and
 * login respawns alike.
 */
@Mixin(EntityPlayer.class)
public class PlayerMixin {

    @Inject(method = "getBedSpawnLocation", at = @At("RETURN"), cancellable = true)
    private static void rs$adjust(World world, BlockPos pos, boolean forced, CallbackInfoReturnable<BlockPos> cir) {
        BlockPos ret = cir.getReturnValue();
        if (ret == null) {
            return;
        }
        Random random = new Random();
        BlockPos current = ret.up();
        while (true) {
            IBlockState state = world.getBlockState(current);
            Block block = state.getBlock();
            if (block instanceof BlockLiquid || block instanceof BlockCauldron
                    || (!(block instanceof BlockBush) && !(block instanceof BlockAir))) {
                current = rs$gt(current.up().up(), random);
            } else if (state.getRenderType() == EnumBlockRenderType.MODEL) {
                current = rs$gt(current.up().up(), random);
            } else {
                break;
            }
        }
        cir.setReturnValue(current);
    }

    @Unique
    private static BlockPos rs$gt(BlockPos pos, Random random) {
        return switch (random.nextInt(3)) {
            case 0 -> rs$gt2(pos.east(), random);
            case 1 -> rs$gt2(pos.south(), random);
            case 2 -> rs$gt2(pos.west(), random);
            default -> pos.east().north();
        };
    }

    @Unique
    private static BlockPos rs$gt2(BlockPos pos, Random random) {
        return switch (random.nextInt(3)) {
            case 0 -> pos.east().east();
            case 1 -> pos.south().south();
            case 2 -> pos.west().west();
            default -> pos.east().north();
        };
    }
}
