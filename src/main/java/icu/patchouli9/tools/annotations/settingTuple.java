package icu.patchouli9.tools.annotations;

import icu.patchouli9.tools.types.EntryType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface settingTuple {
    String varName() default "";
    String description() default "";
    EntryType type();
    int defaultValue() default 0;
    boolean defaultToggle() default false;
}
