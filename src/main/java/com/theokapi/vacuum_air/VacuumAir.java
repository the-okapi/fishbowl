package com.theokapi.vacuum_air;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;


public class VacuumAir implements ModInitializer {
	public static final String MOD_ID = "vacuum_air";

	public static final ConsumableComponent LEVITATION_EFFECT = ConsumableComponents.food()
			.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 5 * 20, 1), 1.0f))
			.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10 * 20, 1), 1.0f))
			.build();
	public static final FoodComponent AIR_BOTTLE_FOOD = new FoodComponent.Builder().alwaysEdible().build();

	public static Item registerItem(String name) {
		RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
		return Registry.register(Registries.ITEM, registryKey.getValue(), new Item(new Item.Settings().registryKey(registryKey)));
	}

	public static Item registerWatchItem(String name) {
		RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
		return Registry.register(Registries.ITEM, registryKey.getValue(), new WatchItem(new Item.Settings().registryKey(registryKey)));
	}

	public static Block registerBlock(String name) {
		RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, name));

		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, registryKey.getValue());

		Block block = new Block(AbstractBlock.Settings.create().registryKey(registryKey).sounds(BlockSoundGroup.METAL).hardness(1.8f));

		BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
		Registry.register(Registries.ITEM, itemKey, blockItem);

		return Registry.register(Registries.BLOCK, registryKey, block);
	}

	public static Item registerFoodItem(String name, FoodComponent food) {
		RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
		return Registry.register(Registries.ITEM, registryKey.getValue(), new Item(new Item.Settings().registryKey(registryKey).food(food)));
	}

	public static Item registerFoodWithEffectItem(String name, FoodComponent food, ConsumableComponent effect) {
		RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
		return Registry.register(Registries.ITEM, registryKey.getValue(), new Item(new Item.Settings().registryKey(registryKey).food(food, effect)));
	}

	public static final Item VACUUM_AIR = registerItem("vacuum_air");
	public static final Item AIR_BOTTLE = registerFoodItem("air_bottle", AIR_BOTTLE_FOOD);
	public static final Item COMPRESSED_AIR_BOTTLE = registerFoodWithEffectItem("compressed_air_bottle", AIR_BOTTLE_FOOD, LEVITATION_EFFECT);

	public static final Item APPLE_WATCH = registerWatchItem("apple_watch");

	public static final Block MICROSOFT_SURFACE = registerBlock("microsoft_surface");

	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
				.register(itemGroup -> {
					itemGroup.add(APPLE_WATCH);
					itemGroup.add(VACUUM_AIR);
					itemGroup.add(AIR_BOTTLE);
					itemGroup.add(COMPRESSED_AIR_BOTTLE);
				});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
				.register(itemGroup -> itemGroup.add(MICROSOFT_SURFACE));
	}
}