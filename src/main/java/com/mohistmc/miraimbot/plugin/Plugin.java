package com.mohistmc.miraimbot.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现监听
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {
    public String name();

    public String version();

    public String[] authors();

    public String description() default "";
}
