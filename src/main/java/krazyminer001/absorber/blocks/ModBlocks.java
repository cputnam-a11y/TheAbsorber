package krazyminer001.absorber.blocks;

import krazyminer001.absorber.TheAbsorber;
import krazyminer001.absorber.blocks.custom.AbsorberBlock;
import krazyminer001.absorber.blocks.custom.WetAbsorberBlock;
import krazyminer001.absorber.sounds.ModSoundGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block ABSORBER = registerBlock("absorber",
            (settings) -> new AbsorberBlock(settings.sounds(ModSoundGroups.ABSORBER)));
    public static final Block FILLED_ABSORBER = registerBlock("filled_absorber",
            (settings) -> new WetAbsorberBlock(settings.sounds(ModSoundGroups.FILLED_ABSORBER)));

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> blockProvider) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TheAbsorber.ModID, name));
        Block block = blockProvider.apply(AbstractBlock.Settings.copy(Blocks.STONE).registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key.getValue(), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TheAbsorber.ModID, name));
        return Registry.register(Registries.ITEM, key.getValue(),
                new BlockItem(block, new Item.Settings().registryKey(key)));
    }

    public static void registerModBlocks() {
        TheAbsorber.LOGGER.info("Registering Mod Blocks");
    }
}
