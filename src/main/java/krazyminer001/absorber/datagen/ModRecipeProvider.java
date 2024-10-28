package krazyminer001.absorber.datagen;

import krazyminer001.absorber.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new ModRecipeGenerator(registryLookup, exporter);
    }

    @Override
    public String getName() {
        return "I don't Even Know";
    }
    //    @Override
//    public void generate(RecipeExporter exporter) {
//

//    }
    private static final class ModRecipeGenerator extends RecipeGenerator {

        private ModRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
            super(registries, exporter);
        }

        @Override
        public void generate() {
            offerSmelting(
                    List.of(ModBlocks.FILLED_ABSORBER),
                    RecipeCategory.BUILDING_BLOCKS,
                    ModBlocks.ABSORBER,
                    0.15f,
                    200,
                    ""
            );
            ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ABSORBER)
                    .pattern("ABA")
                    .pattern("BCB")
                    .pattern("ABA")
                    .input('A', Blocks.DARK_PRISMARINE)
                    .input('B', Blocks.PRISMARINE)
                    .input('C', Blocks.SPONGE)
                    .criterion(hasItem(Blocks.SPONGE), conditionsFromItem(Blocks.SPONGE))
                    .offerTo(exporter);
        }
    }
}
