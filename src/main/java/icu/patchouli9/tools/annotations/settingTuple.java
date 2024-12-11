package icu.patchouli9.tools.annotations;

import java.lang.annotation.*;

import icu.patchouli9.tools.types.EntryType;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface settingTuple {

    String varName() default "";

    String description() default "";

    EntryType type();
}
