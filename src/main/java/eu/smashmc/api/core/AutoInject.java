package eu.smashmc.api.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;

import eu.smashmc.api.SmashComponent;

/**
 * Classes instantiated by {@link AutoRegister} can use this annotation to
 * automatically inject dependencies into fields.
 * 
 * Currently supported dependencies are <br>
 * - {@link SmashComponent}<br>
 * - {@link Logger}<br>
 * - {@link Plugin}<br>
 * - {@link BukkitScheduler}<br>
 * - {@link ScoreboardManager}<br>
 * - {@link Server}<br>
 * - {@link Messenger}<br>
 * - {@link PluginManager}<br>
 * - {@link ConsoleCommandSender}<br>
 * - {@link CommandMap}<br>
 * - {@link ItemFactory}<br>
 * - {@link HelpMap}<br>
 * - {@link ServicesManager}<br>
 * - Bukkit services aswell as {@link RegisteredServiceProvider}<br>
 * 
 * And everything registered using {@link RegistryService#bind(Object)}.
 * 
 * @author LiquidDev
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInject {

}
