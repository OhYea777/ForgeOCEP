/*
 * ForgeOCEP
 * Copyright (C) 2018 Forge Overly Complicated Example Project
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

package examplemod.examplemodlib;

import examplemod.examplemodlib.api.ExampleModLibAPI;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("examplemodlib")
public class ExampleModLib {

    private static ExampleModLib INSTANCE;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleModLib() {
        INSTANCE = this;

        // Register the preInit method for modloading
        FMLModLoadingContext.get().getModEventBus().addListener(this::preInit);

        // Register the init method for modloading (tests the api source)
        FMLModLoadingContext.get().getModEventBus().addListener(this::init);

        // Register ourselves for server, registry and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void preInit(final FMLPreInitializationEvent event) {
        // Some preinit code
        LOGGER.info("HELLO FROM PREINIT");
    }

    private void init(final FMLInitializationEvent event) {
        // Some example code
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        // Init api
        ExampleModLibAPI.init();
    }

    @SubscribeEvent
    public void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        // Register a new block here
        LOGGER.info("HELLO from Register Block");
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public void someLibraryMethod(@Nonnull String mod) {
        LOGGER.info("someLibraryMethod: " + mod);
    }

    public static ExampleModLib getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("[ExampleModLib] Not yet initialised");
        }

        return INSTANCE;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
