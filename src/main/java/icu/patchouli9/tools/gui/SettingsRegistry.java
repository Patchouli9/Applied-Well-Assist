package icu.patchouli9.tools.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import icu.patchouli9.tools.Main;
import icu.patchouli9.tools.ModuleManager.ModuleManager;
import icu.patchouli9.tools.annotations.RegisterSettingEntry;
import icu.patchouli9.tools.annotations.settingTuple;
import icu.patchouli9.tools.types.Section;

public class SettingsRegistry {

    public static List<Section> SECTIONS = new ArrayList<>();

    public static void registerSettings(Class<?> configClass) {
        Section SECTION = new Section();
        RegisterSettingEntry ann = configClass.getAnnotation(RegisterSettingEntry.class);
        SECTION.name = ann.name();
        SECTION.module = ModuleManager.clsToInstance.get(configClass);
        for (settingTuple p : ann.settings()) {
            try {
                Field field = configClass.getField(p.varName());
                SECTION.settings.add(new Section.Setting(p.description(), p.type(), field));

            } catch (Exception e) {
                Main.warn(e);
            }
        }
        SECTIONS.add(SECTION);
    }
}
