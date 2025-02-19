package com.theokapi.vacuum_air;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                createShapeless(RecipeCategory.MISC, VacuumAir.AIR_BOTTLE)
                        .input(Items.GLASS_BOTTLE)
                        .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, VacuumAir.COMPRESSED_AIR_BOTTLE)
                        .input(VacuumAir.AIR_BOTTLE)
                        .input(VacuumAir.VACUUM_AIR)
                        .criterion(hasItem(VacuumAir.AIR_BOTTLE), conditionsFromItem(VacuumAir.AIR_BOTTLE))
                        .criterion(hasItem(VacuumAir.VACUUM_AIR), conditionsFromItem(VacuumAir.VACUUM_AIR))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, VacuumAir.VACUUM_AIR)
                        .pattern("aaa")
                        .pattern("aaa")
                        .pattern("aaa")
                        .input('a', VacuumAir.AIR_BOTTLE)
                        .criterion(hasItem(VacuumAir.AIR_BOTTLE), conditionsFromItem(VacuumAir.AIR_BOTTLE))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, VacuumAir.MICROSOFT_SURFACE, 4)
                        .input(Blocks.WHITE_CONCRETE)
                        .input(Blocks.WHITE_CONCRETE)
                        .input(Blocks.WHITE_CONCRETE)
                        .input(Blocks.WHITE_CONCRETE)
                        .criterion(hasItem(Blocks.WHITE_CONCRETE), conditionsFromItem(Blocks.WHITE_CONCRETE))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, VacuumAir.APPLE_WATCH)
                        .input(Items.APPLE)
                        .input(Items.CLOCK)
                        .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                        .criterion(hasItem(Items.CLOCK), conditionsFromItem(Items.CLOCK))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "RecipeProvider";
    }
}
