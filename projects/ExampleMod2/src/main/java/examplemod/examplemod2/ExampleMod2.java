package examplemod.examplemod2;

import examplemod.examplemod2.api.ExampleMod2API;
import examplemod.examplemodlib.ExampleModLib;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod("examplemod2")
public class ExampleMod2 {

    private static ExampleMod2 INSTANCE;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleMod2() {
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
        ExampleMod2API.init();

        // Run library method
        ExampleModLib.getInstance().someLibraryMethod(FMLModLoadingContext.get().getActiveContainer().getModId());
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

    public static ExampleMod2 getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("[ExampleMod2] Not yet initialised");
        }

        return INSTANCE;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
