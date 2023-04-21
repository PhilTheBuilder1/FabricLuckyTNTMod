package com.luckytntmod.registries;

import com.luckytntmod.LuckyTNTMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {

    public static SoundEvent SAY_GOODBYE = register("say_goodbye");
    public static SoundEvent DEATH_RAY = register("death_ray");
    public static SoundEvent VACUUM_CLEANER_START = register("vacuum_cleaner_start");
    public static SoundEvent VACUUM_CLEANER = register("vacuum_cleaner");

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(LuckyTNTMod.NAMESPACE, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void register() {
        LuckyTNTMod.LOGGER.info("Registering Sound Events...");
    }
}
