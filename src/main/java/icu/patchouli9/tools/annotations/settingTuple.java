package icu.patchouli9.tools.annotations;

import icu.patchouli9.tools.types.EntryType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface settingTuple {
    String varName() default "";
    String description() default "";
    EntryType type();
}
