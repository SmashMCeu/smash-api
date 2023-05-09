package eu.smashmc.api.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.bukkit.event.Listener;

import eu.smashmc.api.SmashComponent;

/**
 * Allows you to automatically instantiate and register listeners, commands and
 * more. Also allows you to use {@link Inject} to automatically inject
 * dependencies into fields and constructors. <br>
 * <br>
 * List of supported registrations: <br>
 * - Bukkit {@link Listener} <br>
 * - Commands using liquidcommands library <br>
 * - {@link SmashComponent} implementations <br>
 * - Your own using {@link RegistryService#addRegistrar(Class, Registrar)} <br>
 * <br>
 * 
 * Instances of all instantiated classes can be retrieved using the
 * {@link RegistryService} helper class. <br>
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
public @interface Managed {

	/**
	 * Use this to add an NMS version contraint, where the class will only be
	 * instantiated and registered when one of the specified NMS versions is
	 * present. If empty or null, this field is ignored.
	 * 
	 * @return comptaible nms versions
	 */
	NmsVersion[] nmsVersion() default {};

	/**
	 * Controls if the annotated type can be instantiated and dependet upon by
	 * another plugin before the owning plugin does.
	 * 
	 * Default is <code>false</code>.
	 * 
	 * @return <code>true</code> if other plugins can instantiate this
	 */
	boolean allowForeignInitialization() default false;

}
