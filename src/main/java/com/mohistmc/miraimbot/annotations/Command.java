package com.mohistmc.miraimbot.annotations;

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
    String name();

    /**
     * @return 指令所有别称
     */
    String[] alias() default {};

    /**
     * @return 指令用法
     */
    String usage() default "";

    /**
     * @return 指令简介
     */
    String description() default "";

    /**
     * @return 指令权限
     */
    String permission() default "";

    /**
     * @return 指令是否在帮助中显示
     */
    boolean show() default true;

    /**
     * 当这一项为true时，如果玩家不是op，即使有permission中的权限，也不执行
     *
     * @return 指令是否只能op执行
     */
    boolean onlyOp() default false;
}
