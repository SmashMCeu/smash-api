package eu.smashmc.api.core;

import eu.smashmc.api.Environment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Equivalent to @{@link Managed} that only gets instantiated when {@link Environment#isDebug()} is true.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Debug {
}
