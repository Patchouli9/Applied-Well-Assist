package icu.patchouli9.awa.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterSettingEntry {

    String name() default "Module";

    settingTuple[] settings() default {};
}
