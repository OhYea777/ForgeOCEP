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

package examplemod.examplemod1;

import examplemod.examplemod1.api.ExampleMod1API;
import examplemod.examplemod1.common.blocks.BlockYellow;
import examplemod.examplemodlib.ExampleModLib;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod1.MOD_ID)
public class ExampleMod1 {

    public static final String MOD_ID = "examplemod1";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ItemGroup ITEM_GROUP = new ItemGroup(MOD_ID) {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(BlockYellow.BLOCK_YELLOW);
        }
    };

    public ExampleMod1() {
        FMLModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some setup code
        getLogger().info("HELLO FROM SETUP");

        // Init api
        ExampleMod1API.init();

        // Run library method
        ExampleModLib.someLibraryMethod(FMLModLoadingContext.get().getActiveContainer().getModId());
    }

    @SubscribeEvent
    public final void registerBlocks(final RegistryEvent.Register<Block> event) {
        // Register blocks
        event.getRegistry().register(BlockYellow.BLOCK_YELLOW);
    }

    @SubscribeEvent
    public final void registerItems(final RegistryEvent.Register<Item> event) {
        Item.Builder itemBuilder = new Item.Builder().group(getItemGroup());

        // Register items
        event.getRegistry().register(new ItemBlock(BlockYellow.BLOCK_YELLOW, itemBuilder).setRegistryName(MOD_ID, "block_yellow"));
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
