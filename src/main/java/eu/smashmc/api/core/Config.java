package eu.smashmc.api.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classes instantiated by {@link Managed} can use this annotation to
 * automatically inject config properties into fields.
 * 
 * @author LiquidDev
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Config {

	/**
	 * Name of the config property.
	 * 
	 * If default or empty, the field name will be used.
	 */
	String value() default "";

}
