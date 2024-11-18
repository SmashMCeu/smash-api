package eu.smashmc.api.lang;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a Bukkit-Plugins main class with this to automatically initialize the language system with the given scope for the plugin.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LanguageScope {
	/**
	 * The language scope. If empty, use the Plugins name.
	 */
	String value() default "";
}
