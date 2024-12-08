package icu.patchouli9.tools;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import icu.patchouli9.tools.ModuleManager.Module;
import icu.patchouli9.tools.ModuleManager.ModuleManager;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        ModuleManager.preinit();
        // Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        Main.LOG.info(Config.greeting);
        Main.LOG.info("I am a Mod at version " + Tags.VERSION);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        try {
            ModuleManager.init();
        } catch (Exception e) {
            Main.LOG.error("Failed in initializing ModuleManager{}", String.valueOf(e));
        }
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)

    public void postInit(FMLPostInitializationEvent event) {

    }

    // register server commands in this event handler (Remove if not needed)

    public void serverStarting(FMLServerStartingEvent event) {}
}
