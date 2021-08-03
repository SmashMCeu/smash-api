# Smash API
Smash-API is a collection of Java APIs for SmashMC that allow you to develop for SmashMC :)

## Usage
The usage should be as simple as getting the desired API by it's class and working with it.
All interfaces are (hopefully) well documented so that there should be no need to know about how they are implemented.

### Get an API
To get an instance/implementation of an API use the `SmashMc` class as the following:
```
SmashMc.getApi(<api-name>.class)
```
Please note that some APIs might not be implemented at runtime depending on the enviroment you are developing for. 
In this case, `SmashMc.getApi()` will throw an `UnsupportedOperationException`.

### List of APIs
The list of currently implemented SmashAPIs:
* Vanish
* Playtime
* Language*
* AsyncDispatcher
* MinecraftIdentityProvider

*For a more conveniente use, you can stick to the `Lang` wrapper in `eu.smashmc.api.lang.Lang`