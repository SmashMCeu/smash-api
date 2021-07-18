# Smash API
Smash-API is a collection of SmashMC's Java APIs that allow anyone to develop for SmashMC :)

## Usage
The usage should be as simple as getting an API by it's class and working with it.
All interfaces are (hopefully) well documented that there is no need to know about the implementation or any other factors.

### Get an API
To get an instance/implementation of an API use the `SmashMc` class as following:
```
SmashMc.getApi(<api-name>.class)
```
Please note that some APIs might not be implemented at runtime depending on the enviroment you are developing for. 
In this case, `SmashMc.getApi()` will throw an `UnsupportedOperationException`.

### List of APIs
The list of currently implemented SmashAPIs:
* VanishApi
* LanguageApi*

*It is easier to use the `Lang` wrapper in `eu.smashmc.api.lang.Lang`