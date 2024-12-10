package icu.patchouli9.tools.gui;

import cpw.mods.fml.common.Mod;
import icu.patchouli9.tools.Main;
import icu.patchouli9.tools.ModuleManager.ModuleManager;
import icu.patchouli9.tools.annotations.RegisterSettingEntry;
import icu.patchouli9.tools.annotations.settingTuple;
import icu.patchouli9.tools.types.EntryType;
import icu.patchouli9.tools.types.Section;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsRegistry {
    public static List<Section> SECTIONS = new ArrayList<>();

    public static void registerSettings(Class<?> configClass) {
        Section SECTION = new Section();
        RegisterSettingEntry ann = configClass.getAnnotation(RegisterSettingEntry.class);
        SECTION.name = ann.name();
        SECTION.module = ModuleManager.clsToInstance.get(configClass);
        for (settingTuple p : ann.settings()) {
            Main.info("???___???");
            try {
                Field field = configClass.getField(p.varName());
                Object defVal;
                if (p.type() == EntryType.NUMBER) {
                    defVal = p.defaultValue();
                } else {
                    defVal = p.defaultToggle();
                }
                SECTION.settings.add(new Section.Setting(p.description(), p.type(), defVal, field));

            } catch (Exception e) {
                Main.warn(Arrays.toString(e.getStackTrace()));
            }
        }
        SECTIONS.add(SECTION);
    }
}
