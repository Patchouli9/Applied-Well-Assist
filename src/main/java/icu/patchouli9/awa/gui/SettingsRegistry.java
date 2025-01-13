package icu.patchouli9.awa.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import icu.patchouli9.awa.Main;
import icu.patchouli9.awa.annotations.RegisterSettingEntry;
import icu.patchouli9.awa.annotations.settingTuple;
import icu.patchouli9.awa.types.Section;

public class SettingsRegistry {

    public static List<Section> SECTIONS = new ArrayList<>();

    public static void registerSettings(Object module) {
        Class<?> clazz = module.getClass();
        RegisterSettingEntry ann = clazz.getAnnotation(RegisterSettingEntry.class);
        if (ann == null) {
            return;
        }
        Section SECTION = new Section();
        SECTION.name = ann.name();
        SECTION.module = module;
        for (settingTuple p : ann.settings()) {
            try {
                Field field = clazz.getField(p.varName());
                SECTION.settings.add(new Section.Setting(p.description(), p.type(), field));

            } catch (Exception e) {
                Main.warn(e);
            }
        }
        SECTIONS.add(SECTION);
    }
}
