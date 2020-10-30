package com.mohistmc.miraimbot.cmds.manager.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现指令的注册
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * @return 指令名称
     */
    public String name();

    /**
     * @return 指令所有别称
     */
    public String[] alias() default {};

    /**
     * @return 指令用法
     */
    public String usage() default "";

    /**
     * @return 指令简介
     */
    public String description() default "";

    /**
     * @return 指令是否在帮助中显示
     */
    public boolean show() default true;

    /**
     * @return 指令是否只能op执行
     */
    public boolean onlyOp() default false;
}
