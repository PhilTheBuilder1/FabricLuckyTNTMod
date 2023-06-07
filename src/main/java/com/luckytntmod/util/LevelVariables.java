package com.luckytntmod.util;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldAccess;

public class LevelVariables extends PersistentState {
    public int doomsdayTime = 0;
    public int toxicCloudsTime = 0;
    public int iceAgeTime = 0;
    public int heatDeathTime = 0;
    public int tntRainTime = 0;

    public static LevelVariables clientSide = new LevelVariables();

    public NbtCompound save(NbtCompound tag) {
        tag.putInt("doomsdayTime", doomsdayTime);
        tag.putInt("toxicCloudsTime", toxicCloudsTime);
        tag.putInt("iceAgeTime", iceAgeTime);
        tag.putInt("heatDeathTime", heatDeathTime);
        tag.putInt("tntRainTime", tntRainTime);
        return tag;
    }

    public static LevelVariables load(NbtCompound tag) {
        LevelVariables variables = new LevelVariables();
        variables.read(tag);
        return variables;
    }

    public void read(NbtCompound tag) {
        doomsdayTime = tag.getInt("doomsdayTime");
        toxicCloudsTime = tag.getInt("toxicCloudsTime");
        iceAgeTime = tag.getInt("iceAgeTime");
        heatDeathTime = tag.getInt("heatDeathTime");
        tntRainTime = tag.getInt("tntRainTime");
    }

    public static LevelVariables get(WorldAccess level) {
        if(level instanceof ServerWorldAccess sLevel)
            return sLevel.getServer().getOverworld().getPersistentStateManager().getOrCreate(LevelVariables::load, LevelVariables::new, "ltm_level_variables");
        else
            return clientSide;
    }

    public void sync(ServerWorld level) {
        markDirty();
        //ServerPlayNetworking.send(PacketSender.with(level::getDimension), new ClientCommandC2SPacket(this));
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return null;
    }
}
