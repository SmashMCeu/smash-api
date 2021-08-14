# Smash API
Smash-API is a collection of our Java APIs that allow you to develop for SmashMC :)

## Components
All components annotated with `@SmashComponent` can be retrieved using the `SmashMc` class.
The usage should be as simple as getting the desired component by it's class and working with it.
All interfaces are (hopefully) well documented so that there should be no need to know about how they are implemented.

### Get a component
To get a component simply use the `SmashMc` class as the following:
```
SmashMc.getComponent(<component-name>.class)
```
Please note that some Components/APIs might not be implemented at runtime depending on the enviroment you are developing for. 
In this case, `SmashMc.getComponent()` will throw an `UnsupportedOperationException`.

### List of components
The list of currently implemented SmashComponents:
* Vanish
* Playtime
* Language*
* AsyncDispatcher
* MinecraftIdentityProvider
* Statistics

*For a more conveniente use, you can stick to the `Lang` wrapper in `eu.smashmc.api.lang.Lang`