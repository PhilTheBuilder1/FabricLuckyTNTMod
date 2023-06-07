package com.luckytntmod;


import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.LevelVariables;
import com.luckytntmod.util.NuclearBombLike;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class ClientAccess {

    public static void setEntityStringTag(String name, String tag, int id) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        Entity ent = minecraft.world.getEntityById(id);
        if(ent != null) {
            ent.getPersistentData().putString(name, tag);
        }
    }

    public static void setEntityBooleanTag(String name, boolean tag, int id) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        Entity ent = minecraft.world.getEntityById(id);
        if(ent != null) {
            ent.getPersistentData().putBoolean(name, tag);
        }
    }

    public static void setEntityIntTag(String name, int tag, int id) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        Entity ent = minecraft.world.getEntityById(id);
        if(ent != null) {
            ent.getPersistentData().putInt(name, tag);
        }
    }

    public static void syncLevelVariables(LevelVariables variables) {
         LevelVariables.clientSide = variables;
    }

    public static void setToxicCloudData(double size, int id) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        Entity ent = minecraft.world.getEntityById(id);
        if(ent != null) {
            ent.getPersistentData().putDouble("size", size);
        }
    }

    public static void updateEntityIntNBT(String nbt, int value) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if(player != null) {
            player.getPersistentData().putInt(nbt, value);
        }
    }

    public static void displayHydrogenBombParticles(int id) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        Entity ent = minecraft.world.getEntityById(id);
        if(ent != null) {
            if(ent instanceof IExplosiveEntity ient) {
                if(ient.getEffect() instanceof NuclearBombLike effect) {
                    effect.displayMushroomCloud(ient);
                }
            }
        }
    }
}