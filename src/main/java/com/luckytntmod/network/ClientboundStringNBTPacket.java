package com.luckytntmod.network;

import net.minecraft.network.PacketByteBuf;

/*public class ClientboundStringNBTPacket {
    public final String name;
    public final String tag;
    public final int entityId;

    public ClientboundStringNBTPacket(String name, String tag, int entityId) {
        this.name = name;
        this.tag = tag;
        this.entityId = entityId;
    }

    public ClientboundStringNBTPacket(PacketByteBuf buffer) {
        name = buffer.readString();
        tag = buffer.readString();
        entityId = buffer.readInt();

    }

    public void encode(PacketByteBuf buffer) {
        buffer.writeString(name);
        buffer.writeString(tag);
        buffer.writeInt(entityId);
    }

    public void handle(Supplier<> ctx) {
        ctx.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Client(name, tag, entityId));
        });
        ctx.get().setPacketHandled(true);
    }
}*/