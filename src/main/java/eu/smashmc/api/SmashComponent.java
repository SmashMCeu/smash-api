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

	/**
	 * A fallback implementation of the component in case none was registered during
	 * runtime. Usually used in development or debug environments.
	 *
	 * Please make sure the fallback implementation has a default constructor and
	 * implements the annotated interface.
	 * 
	 * @return Class of fallback implementation. {@link Object} if non is present.
	 */
	Class<?> fallbackImpl() default Object.class;
}
