package dev.geri.obamium;

import dev.geri.obamium.items.*;
import dev.geri.obamium.other.Bamba;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Obamium implements ModInitializer {

    // Settings & shit
    private static final String MODID = "obamium";
    private static final Logger LOGGER = LoggerFactory.getLogger("Obamium");
    private static final FabricItemGroupBuilder OBAMA_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "cursed"));

    // todo: make it cursed in lang times

    // Items
    private final LinkedHashMap<String, Item> items = new LinkedHashMap<>() {{
        this.put("obamium", new Item(new FabricItemSettings().rarity(Rarity.EPIC)));
        this.put("obamium_shard", new Item(new FabricItemSettings().rarity(Rarity.EPIC)));
        this.put("obamium_helmet", new ArmorItem(ObarmourMaterial.INSTANCE, EquipmentSlot.HEAD, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
        this.put("obamium_chestplate", new ArmorItem(ObarmourMaterial.INSTANCE, EquipmentSlot.CHEST, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
        this.put("obamium_leggings", new ArmorItem(ObarmourMaterial.INSTANCE, EquipmentSlot.LEGS, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
        this.put("obamium_boots", new ArmorItem(ObarmourMaterial.INSTANCE, EquipmentSlot.FEET, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
    }};

    // Tools
    private final LinkedHashMap<String, ToolItem> toolItems = new LinkedHashMap<>() {{
        this.put("obamium_sword", new SwordItem(ObamiumToolMaterial.INSTANCE, 9, 0.8f, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
        this.put("obamium_pickaxe", new Pickaxe(ObamiumToolMaterial.INSTANCE, 6, 0.6f, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
        this.put("obamium_axe", new Axe(ObamiumToolMaterial.INSTANCE, 11, 0.5f, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
        this.put("obamium_shovel", new ShovelItem(ObamiumToolMaterial.INSTANCE, 0.5f, 1.2f, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
        this.put("obamium_hoe", new Hoe(ObamiumToolMaterial.INSTANCE, 2, 3f, new Item.Settings().rarity(Rarity.EPIC).fireproof()));
    }};

    // Blocks
    private final LinkedHashMap<String, Map.Entry<Block, BlockItem>> blocks = new LinkedHashMap<>() {{
        Block obamiumBlock = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));
        Block obamiumOre = new Block(FabricBlockSettings.of(Material.METAL).requiresTool().strength(4.0f, 4.0f));
        this.put("obamium_ore", Map.entry(obamiumBlock, new BlockItem(obamiumBlock, new FabricItemSettings())));
        this.put("obamium_block", Map.entry(obamiumOre, new BlockItem(obamiumOre, new FabricItemSettings())));
    }};

    private final ConfiguredFeature<?, ?> endConfiguredFeature = new ConfiguredFeature<>(Feature.ORE,
            new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.END_STONE),
                    blocks.get("obamium_ore").getKey().getDefaultState(),
                    4 // Vein size
            ));

    private final PlacedFeature endPlacedFeature = new PlacedFeature(
            RegistryEntry.of(endConfiguredFeature),
            Arrays.asList(
                    CountPlacementModifier.of(10), // Per chunk
                    SquarePlacementModifier.of(), // Horizontal
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop()) // Height
            )
    );

    @Override
    public void onInitialize() {

        ArrayList<ItemStack> registeredItems = new ArrayList<>();

        // Register items
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            Item registeredItem = Registry.register(Registry.ITEM, new Identifier(MODID, entry.getKey()), entry.getValue());
            registeredItems.add(new ItemStack(registeredItem));
        }

        // Register tools
        for (Map.Entry<String, ToolItem> entry : toolItems.entrySet()) {
            Item registeredItem = Registry.register(Registry.ITEM, new Identifier(MODID, entry.getKey()), entry.getValue());
            registeredItems.add(new ItemStack(registeredItem));
        }

        // Register blocks
        for (Map.Entry<String, Map.Entry<Block, BlockItem>> entry : blocks.entrySet()) {
            Registry.register(Registry.BLOCK, new Identifier(MODID, entry.getKey()), entry.getValue().getKey());
            Item registeredItem = Registry.register(Registry.ITEM, new Identifier(MODID, entry.getKey()), entry.getValue().getValue());
            registeredItems.add(new ItemStack(registeredItem));
        }

        Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "bamba"), new Bamba());

        // Create creative menu group
        OBAMA_GROUP.appendItems(items -> items.addAll(registeredItems)).icon(() -> new ItemStack(items.get("obamium"))).build();

        // World generation
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MODID, "end_ore"), endConfiguredFeature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MODID, "end_ore"), endPlacedFeature);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                BiomeKeys.END_HIGHLANDS,
                BiomeKeys.END_BARRENS,
                BiomeKeys.SMALL_END_ISLANDS
        ), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MODID, "end_ore")));
    }


    public static Logger getLogger() {
        return LOGGER;
    }

}