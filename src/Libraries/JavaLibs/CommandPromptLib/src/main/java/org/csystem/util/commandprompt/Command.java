package org.csystem.util.commandprompt;

import java.lang.annotation.*;

@Deprecated(since = "2.0.0", forRemoval = true)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Commands.class)
public @interface Command {
    String value() default "";
}
