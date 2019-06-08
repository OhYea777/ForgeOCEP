/*
 * ForgeOCEP
 * Copyright (C) 2019 Forge Overly Complicated Example Project
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 */

package examplemod.examplemod2;

import examplemod.examplemod2.api.ExampleMod2API;
import examplemod.examplemod2.common.blocks.BlockBlue;
import examplemod.examplemodlib.ExampleModLib;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod2.MOD_ID)
public class ExampleMod2 {

    public static final String MOD_ID = "examplemod2";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ItemGroup ITEM_GROUP = new ItemGroup(MOD_ID) {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(BlockBlue.BLOCK_BLUE);
        }
    };

    public ExampleMod2() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, this::registerBlocks);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, this::registerItems);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some setup code
        getLogger().info("HELLO FROM SETUP");

        // Init api
        ExampleMod2API.init();

        // Run library method
        ExampleModLib.someLibraryMethod(ModLoadingContext.get().getActiveContainer().getModId());
    }

    private void registerBlocks(final RegistryEvent.Register<Block> event) {
        // Register blocks
        getLogger().info("HELLO FROM REGISTER BLOCKS");

        event.getRegistry().register(BlockBlue.BLOCK_BLUE);
    }

    private void registerItems(final RegistryEvent.Register<Item> event) {
        // Register items
        Item.Properties itemBuilder = new Item.Properties().group(getItemGroup());

        getLogger().info("HELLO FROM REGISTER ITEMS");

        event.getRegistry().register(new BlockItem(BlockBlue.BLOCK_BLUE, itemBuilder).setRegistryName(MOD_ID, BlockBlue.NAME));
        event.getRegistry().register(new Item(itemBuilder).setRegistryName(MOD_ID, "blue_ingot"));
        event.getRegistry().register(new Item(itemBuilder).setRegistryName(MOD_ID, "blue_nugget"));
    }

    @SubscribeEvent
    public final void serverStarting(final FMLServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static ItemGroup getItemGroup() {
        return ITEM_GROUP;
    }

}
