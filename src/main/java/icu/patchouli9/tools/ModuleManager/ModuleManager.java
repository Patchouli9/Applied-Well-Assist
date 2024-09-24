package icu.patchouli9.tools.ModuleManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.google.gson.JsonElement;

import icu.patchouli9.tools.Config;
import icu.patchouli9.tools.Main;
import icu.patchouli9.tools.modules.Fly;
import icu.patchouli9.tools.modules.NoFall;

public class ModuleManager {

    public static ArrayList<Module> modules;

    public static void preinit() {
        Config.init();
        try {
            getModules();
        } catch (IllegalAccessException e) {
            Main.LOG.warn("GetModules failed", new RuntimeException(e));
        }
    }

    public static void init() {
        if (ModuleManager.modules != null && Config.json != null) {
            for (Map.Entry<String, JsonElement> entry : Config.json.entrySet()) {
                String name = entry.getKey();
                JsonElement value = entry.getValue();
                ModuleManager.modules.stream()
                    .filter(module -> module.name.equals(name))
                    .forEach(module -> module.enabled = value.getAsBoolean());
            }
        }
    }

    public static void getModules() throws IllegalAccessException {
        modules = new ArrayList<>();
        for (Field field : modulesClass.class.getDeclaredFields()) {
            modules.add((Module) field.get(null));
        }
    }

    static class modulesClass {

        public static Module NoFall = new NoFall("NoFall", Keyboard.KEY_N);
        // public static Module XYZ = new XYZ("XYZ", Keyboard.KEY_X);
        public static Module Fly = new Fly("Fly", Keyboard.KEY_H);
        // public static Module Speed = new Speed("Speed", Keyboard.KEY_C);

        // public static Module Suicide = new Suicide("Suicide", Keyboard.KEY_G);
    }
}
