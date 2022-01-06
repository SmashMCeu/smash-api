package eu.smashmc.api.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import eu.smashmc.api.SmashComponent;

/**
 * Classes instantiated by {@link AutoRegister} can use this annotation to
 * automatically inject dependencies into fields.
 * 
 * Currently supported dependencies are {@link SmashComponent}, {@link Logger}
 * and {@link Plugin} instances.
 * 
 * @author LiquidDev
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInject {

}
