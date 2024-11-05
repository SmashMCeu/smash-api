package eu.smashmc.api.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Automatically invokes methods in classes annotated with {@link Managed} when the owning plugin is disabled.
 *
 * @author LiquidDev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Shutdown {

}
