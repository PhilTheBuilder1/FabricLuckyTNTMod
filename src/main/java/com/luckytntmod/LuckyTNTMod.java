package com.luckytntmod;

import com.luckytntmod.block.LTNTBlock;
import com.luckytntmod.registries.BlockRegistry;
import com.luckytntmod.registries.SoundRegistry;
import com.luckytntmod.registryHelper.RegistryHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckyTNTMod implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("fabricluckytntmod");
	public static final String NAMESPACE = "luckytntmod";
	public static final RegistryHelper RH = new RegistryHelper();
	private static ItemGroup NORMAL_TNT;
	private static ItemGroup GOD_TNT;
	private static ItemGroup DOOMSDAY_TNT;
	public static World accessWorld = null;
	@Override
	public void onInitialize() {
		BlockRegistry.init();
		registerGroups();
		addItemsToGroup();
		SoundRegistry.register();
		for(Pair<LTNTBlock, Item> pair : RegistryHelper.TNT_DISPENSER_REGISTRY_LIST) {
			LTNTBlock block = pair.getLeft();
			Item item = pair.getRight();
			DispenserBehavior behaviour = (source, stack) -> {
				World level = source.getWorld();
				BlockPos pos = new BlockPos(DispenserBlock.getOutputLocation(source));
				LTNTBlock.primeTnt(level, pos, null, block);
				stack.decrement(1);
				return stack;
			};
			DispenserBlock.registerBehavior(item, behaviour);
		}
	}

	public static void registerGroups() {
		NORMAL_TNT = FabricItemGroup.builder(new Identifier(NAMESPACE, "normal_tnt"))
				.displayName(Text.translatable("item_group." + NAMESPACE + ".normal_tnt"))
				.icon(() -> new ItemStack(BlockRegistry.TNT_X5))
				.build();
		GOD_TNT = FabricItemGroup.builder(new Identifier(NAMESPACE, "god_tnt"))
				.displayName(Text.translatable("item_group." + NAMESPACE + ".god_tnt"))
				.icon(() -> new ItemStack(BlockRegistry.TNT_X2000))
				.build();
		DOOMSDAY_TNT = FabricItemGroup.builder(new Identifier(NAMESPACE, "doomsday_tnt"))
				.displayName(Text.translatable("item_group." + NAMESPACE + ".doomsday_tnt"))
				.icon(() -> new ItemStack(BlockRegistry.TNT_X10000))
				.build();
	}

	public static void addItemsToGroup() {
		for(LTNTBlock block : RH.registeredBlocks) {
			if(block.tab.equals("n")) addItem(NORMAL_TNT, block.asItem());
			if(block.tab.equals("g")) addItem(GOD_TNT, block.asItem());
			if(block.tab.equals("d")) addItem(DOOMSDAY_TNT, block.asItem());
		}
	}

	public static void addItem(ItemGroup group, Item item) {
		ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
	}
}