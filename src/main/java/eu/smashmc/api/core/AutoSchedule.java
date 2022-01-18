package eu.smashmc.api.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Automatically schedules methods in classes annotated with
 * {@link AutoRegister}.
 * 
 * @author LiquidDev
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoSchedule {

	/**
	 * The delay in ticks to execute the method first time.
	 * 
	 * Default is -1, meaning it will use the period delay if set. If both delay and
	 * period are negative, an {@link IllegalArgumentException} is thrown.
	 */
	int delay() default -1;

	/**
	 * The delay in ticks to periodically execute the method.
	 * 
	 * Default is -1, which disables repeating.
	 */
	int period() default -1;

	/**
	 * If the method should be scheduled asynchronously.
	 */
	boolean async() default false;
}
