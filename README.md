# Smash API
Smash-API is the collection of our Java APIs that allow plugins to communicate without depending on each others implementation. The API is designed for Spigot and Bungeecord plugins, but can also be used in standalone applications (with some limitations).

## Components
The Smash-API is split into multiple Components. Each component is independent from another and usually implemented in it's own plugin.
All components annotated with `@SmashComponent` can be retrieved using the `SmashMc` class.
The usage should be as simple as getting the desired component by it's class and working with it.
All interfaces are (hopefully) well documented so that there is no need to know about how they are implemented.

### Get a component
To get a component simply use the `SmashMc` class as the following:
```
SmashMc.getComponent(<component-name>.class)
```
Please note that some Components might not be present at runtime, depending on the enviroment you are developing for. 
In this case, `SmashMc.getComponent()` will throw an `UnsupportedOperationException`. You can check the components `@SmashComponent` annotation to see supported environments.

### List of components
The list of currently implemented SmashComponents:
* Vanish
* Playtime
* Language*
* AsyncDispatcher
* MinecraftIdentityProvider
* Achievements
* LobbyTos
* Statistics
* PlayerProfileService
* Economy
* Friends
* PermissionsProvider
* ProxyService

*For a more conveniente use, you can stick to the `Lang` wrapper in `eu.smashmc.api.lang.Lang`


## @AutoRegister and @AutoInject
If you are developing for a Bukkit environment, you can automatically register your listeners and commands with basic dependency injection using the `@AutoRegister` and `@AutoInject` annotations.
```java
@AutoRegister
public class ExampleListener implements Listener {

	@AutoInject
	private Economy economy;

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		var player = event.getPlayer();
		var uuid = player.getUniqueId();
		var coins = economy.getBalance(uuid, Currency.COINS);
		player.sendMessage("Your balance: " + coins);
	}
}
```

Injection also works for constructor parameters:
```java
@AutoRegister
public class ExampleCommand extends PlayerCommand {

	public ExampleCommand(Logger logger, Plugin plugin) {
		super(Constants.PREFIX, "example", "");
		
		logger.info("I'm an injected logger!");
		plugin.getServer().broadcastMessage("I'm an injected plugin instance!");
	}

	@Override
	protected void onCommand(Player sender, Arguments args) throws CommandFailException {
		// TODO
	}
}
```

For more information, please refer to the javadoc of `@AutoRegister`.
