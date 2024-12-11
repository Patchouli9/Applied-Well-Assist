package icu.patchouli9.tools.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import icu.patchouli9.tools.types.EntryType;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface settingTuple {

    String varName() default "";

    String description() default "";

    EntryType type();
}
