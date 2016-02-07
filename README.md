skRayFall
=========

![logo](https://puu.sh/j9sy5/066b81b74f.png "")

## Download
skRayFall is officially hosted on bukkit and can be downloaded [here](http://dev.bukkit.org/bukkit-plugins/skrayfall/).

## Documentation
All documentation for using skRayFall can be found [here](http://www.skunity.com/SkRayFall).

## Description
skRayFall is an addon for the [Skript language](http://dev.bukkit.org/bukkit-plugins/skript/). Get the bleeding edge tools and features that will help make your server come alive and take it to the next level. Skript is the most popular high level coding language designed for minecraft, allowing you to make awesome features faster and easier then in java.

## Features
Currently skRayFall adds over 50 extremely powerful syntax elements to the skript language and bridges many popular plugins into the language. These plugins include:

* [Citizens](http://dev.bukkit.org/bukkit-plugins/citizens/)
* [CrackShot](http://dev.bukkit.org/bukkit-plugins/crackshot/)
* [Builder](http://dev.bukkit.org/bukkit-plugins/builder-citizens2/)
* [EffectLib](http://dev.bukkit.org/bukkit-plugins/effectlib/)
* [Holographic Displays](http://dev.bukkit.org/bukkit-plugins/holographic-displays/)
* [Votifier](http://dev.bukkit.org/bukkit-plugins/votifier/)
* [BossBarAPI](https://www.spigotmc.org/resources/api-bossbarapi-1-7-1-8.7504/)
* [Capes](https://www.spigotmc.org/resources/capes.9068/)
* [CoreProtect](http://dev.bukkit.org/bukkit-plugins/coreprotect/)

It adds many new possibilities like being able to control the path finding of NPC's, changing the sidebar, tablist, undername of players and even contains many features for RPG servers like events for when a certain slot is clicked on the player's crafting inventory.

## Usage
To install skRayFall first make sure you have skript installed. Then just add the jar to your plugins folder and code away! Check out our documentation above for a full list of all possibilities. Here is a small example on how easy it can be to make custom scoreboards for individual players:

```
command /sidebar:
	trigger:
		set name of sidebar of player to "&eI'm a sidebar!"
		set score "&5A Score" in sidebar of player to 10
```
More examples can be found on the Skript [forums](http://dev.bukkit.org/bukkit-plugins/skript/forum/) and the [skUnity forums](http://forums.skunity.com/).

Legacy Versions of skRayFall can be found [here](http://dev.bukkit.org/bukkit-plugins/skript/forum/misc/70069-addon-sk-ray-fall-1-6/).

## Updater
This plugin checks Bukkit Dev for updates when it starts up and will let you know about them. This feature can be disabled in the config by setting "UpdateAlerts" to false. If you do not see an option for it please delete your config and restart the server to allow it to reload. Then adjust the settings and restart the server once more.

## Metrics
Usage metrics for skRayFall can be found [here](http://mcstats.org/plugin/skRayFall).
