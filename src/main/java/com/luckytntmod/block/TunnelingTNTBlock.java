package com.luckytntmod.block;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.network.PacketHandler;
import com.luckytntmod.tnteffects.TunnelingTNTEffect;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class TunnelingTNTBlock extends LTNTBlock {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public TunnelingTNTBlock(Settings settings, int index, String tab) {
        super(settings, index, tab);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(FACING, context.getPlayerFacing());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    public ActionResult onUse(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult result) {
        ItemStack itemstack = player.getStackInHand(hand);
        if (!itemstack.isOf(Items.FLINT_AND_STEEL) && !itemstack.isOf(Items.FIRE_CHARGE)) {
            return super.onUse(state, level, pos, player, hand, result);
        } else {
            TunnelingTNTBlock.primeTnt(level, pos, player);
            level.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            Item item = itemstack.getItem();
            if (!player.isCreative()) {
                if (itemstack.isOf(Items.FLINT_AND_STEEL)) {
                    itemstack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));
                } else {
                    itemstack.decrement(1);
                }
            }

            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.success(level.isClient);
        }
    }

    public static void primeTnt(World world, BlockPos pos, @Nullable LivingEntity igniter) {
        LTNTBlock block = (LTNTBlock) world.getBlockState(pos).getBlock();
        LTNTEntity tnt = LuckyTNTMod.RH.registeredEntities.get(block.index).create(world);
        if(tnt == null) return;
        tnt.getPersistentData().putString("direction", world.getBlockState(pos).getBlock() instanceof TunnelingTNTBlock ? world.getBlockState(pos).get(FACING).getName() : "east");
        System.out.println("Thing: " + tnt.getPersistentData().getString("direction"));
        tnt.setPosition(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
        tnt.effect.setState(tnt);
        System.out.println("Thing: " + tnt.getPersistentData().getString("direction"));
        world.spawnEntity(tnt);
        world.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.MASTER, 1.0F, 1.0F);
        world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        if(!block.shouldRandomlyFuse) {
            tnt.setVelocity(0, 0, 0);
        }
        if(world.isClient()) return;
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(tnt.getId());
        buf.writeString(tnt.getPersistentData().getString("direction"));
        for(ServerPlayerEntity player : PlayerLookup.world(((ServerWorld) world))) {
            ServerPlayNetworking.send(player, PacketHandler.TUNNELING_TNT_INIT, buf);
        }
    }
}
