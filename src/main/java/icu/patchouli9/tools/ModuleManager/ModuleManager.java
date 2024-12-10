package icu.patchouli9.tools.ModuleManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import icu.patchouli9.tools.EventHandler;
import icu.patchouli9.tools.gui.SettingsRegistry;
import icu.patchouli9.tools.modules.*;
import org.lwjgl.input.Keyboard;

import com.google.gson.JsonElement;

import icu.patchouli9.tools.Config;
import icu.patchouli9.tools.Main;

public class ModuleManager {
    public static ArrayList<Module> modules = new ArrayList<>();
    public static Map<Class<?>, Module> clsToInstance = new HashMap<>();
    public static void preinit() {
        Config.init();
        try {
            getModules();
        } catch (IllegalAccessException e) {
            Main.LOG.warn("GetModules failed", new RuntimeException(e));
        }
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, GuiHandler.instance);
    }

    public static void init() throws Exception {
        if (ModuleManager.modules != null && Config.json != null) {
            for (Map.Entry<String, JsonElement> entry : Config.json.entrySet()) {
                String name = entry.getKey();
                JsonElement value = entry.getValue();
                ModuleManager.modules.stream()
                    .filter(module -> module.name.equals(name))
                    .forEach(module -> module.enabled = value.getAsBoolean());
            }
            for(Module module: ModuleManager.modules){
                ClientRegistry.registerKeyBinding(module.keybind);
                module.init();
            }
        }
        new EventHandler().init();
    }

    public static void getModules() throws IllegalAccessException {
        for (Field field : modulesClass.class.getDeclaredFields()) {
            Module module = (Module) field.get(null);
            modules.add(module);
            Class<?> clazz=module.getClass();
            clsToInstance.put(clazz, module);
        }
        SettingsRegistry.registerSettings(Fly.class);

    }
    static class modulesClass {
        public static Module NoFall = new NoFall("NoFall", Keyboard.KEY_N);
        // public static Module XYZ = new XYZ("XYZ", Keyboard.KEY_X);
        public static Module Fly = new Fly("Fly", Keyboard.KEY_H);
        public static Module AutoHit = new AutoHit("AutoHit", Keyboard.KEY_EQUALS);
        public static Module InventoryMove = new InventoryMove("InventoryMove", Keyboard.KEY_M);
        public static Module GuiTest = new GuiTest("GuiTest", Keyboard.KEY_G);
        public static Module Speed = new Speed("Speed", Keyboard.KEY_C);
        public static Module AntiNegative = new AntiNegative("AntiNegative", Keyboard.KEY_C);
        // public static Module Suicide = new Suicide("Suicide", Keyboard.KEY_G);
    }
}
// TODO: 在按住方向键, 打开 E再关闭, 需要再按下方向键.
