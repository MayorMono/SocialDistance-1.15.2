# SocialDistance for Minecraft Bukkit 1.15.2
A plugin that forces players to practice social distance amongst themselves, as well as animals and monsters. When a player violates social distancing rules, they will be given the Wither potion effect with a set amplifier for a set amount of time.

**Commands**
* /penable: initiates a pandemic
* /pdisable: ends the pandemic
* /pstatus: displays the status of the pandemic
* /psetradius <radius>: sets danger radius in range [1,127]
* /psetduration <seconds>: sets the infection duration in range [1,1200]
* /psetamplifier <amplifier>: sets infection amplifier in ranger [0,5]
* /ptogglemobs: toggles infection of mobs
  
**Suggested Settings for Survival Mode**
* radius: 4
* duration: 3 seconds
* amplifier: 1
* mobs infected: true
