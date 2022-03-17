package dev.geri.obamium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Obamium implements ModInitializer {

    // Settings & shit
    private static final String MODID = "obamium";
    private static final Logger LOGGER = LoggerFactory.getLogger("Obamium");

    // todo: make it cursed in lang times

    // Items
    private static final Item OBAMIUM_ITEM = new Item(new FabricItemSettings().rarity(Rarity.EPIC));
    private static final Item OBAMIUM_SHARD = new Item(new FabricItemSettings().rarity(Rarity.EPIC));

    private static final Block OBAMIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    private static final Block OBAMIUM_ORE = new Block(FabricBlockSettings.of(Material.METAL).strength(1, 1.0f));
    private static final Item OBAMIUM_BLOCK_ITEM = new BlockItem(OBAMIUM_BLOCK, new FabricItemSettings());
    private static final Item OBAMIUM_ORE_ITEM = new BlockItem(OBAMIUM_ORE, new FabricItemSettings());

    private static final ItemGroup OBAMA_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "cursed"))
            .icon(() -> new ItemStack(OBAMIUM_ITEM))
            .appendItems(items -> {
                items.add(new ItemStack(OBAMIUM_ITEM));
                items.add(new ItemStack(OBAMIUM_SHARD));
                items.add(new ItemStack(OBAMIUM_BLOCK_ITEM));
                items.add(new ItemStack(OBAMIUM_ORE_ITEM));
            }).build();

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "obamium"), OBAMIUM_ITEM);
        Registry.register(Registry.ITEM, new Identifier(MODID, "obamium_shard"), OBAMIUM_SHARD);
        Registry.register(Registry.BLOCK, new Identifier(MODID, "obamium_block"), OBAMIUM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MODID, "obamium_block"), OBAMIUM_BLOCK_ITEM);
        Registry.register(Registry.BLOCK, new Identifier(MODID, "obamium_ore"), OBAMIUM_ORE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "obamium_ore"), OBAMIUM_ORE_ITEM);
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
