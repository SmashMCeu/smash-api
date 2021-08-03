package eu.smashmc.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation interface for components that can be registered as a
 * {@link SmashMc}-Component.
 * 
 * @author LiquidDev
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SmashComponent {
	/**
	 * {@link Environment} that are supported by the API. Will be checked when
	 * getting the API. By default ({@link Environment} NONE) it should is supported
	 * by any.
	 */
	Environment[] value() default { Environment.NONE };
}
