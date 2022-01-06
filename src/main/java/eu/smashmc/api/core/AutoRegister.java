package eu.smashmc.api.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.bukkit.event.Listener;

import eu.smashmc.api.SmashComponent;

/**
 * TODO Experimental<br>
 * <br>
 * 
 * Allows you to automatically instantiate and register listeners, commands and
 * more. Also allows you to use {@link AutoInject} to automatically inject
 * dependencies into fields and constructors. <br>
 * <br>
 * List of supported registrations: <br>
 * - Bukkit {@link Listener} <br>
 * - Commands using liquidcommands library <br>
 * - {@link SmashComponent} implementations <br>
 * - TODO: soon more <br>
 * <br>
 * 
 * Instances of all instantiated classes can be retrieved using the
 * {@link Registered} helper class. <br>
 * <br>
 *
 * Reasons for why a class might not automatically be registered: <br>
 * - The Plugin does not depend on SmashCore<br>
 * - The class is not in a package starting with "eu.smashmc"<br>
 * - The class or it's package name contain the word 'bungee' (case insensitive)
 * <br>
 * 
 * @author LiquidDev
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoRegister {

}
