package com.meeku.socialdistance;

import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public class SocialDistance extends JavaPlugin {
	public static boolean enabled = false;
	public static int duration = 5;
	public static int amplifier = 2;
	public static int radius = 2;
	public static boolean mobsInfected = true;
	
	@Override
	public void onEnable() {
		System.out.println("Social Distance by MayorMono");
		
		// Register commands
		this.getCommand("penable").setExecutor(new Enabler());
		this.getCommand("pdisable").setExecutor(new Disabler());
		this.getCommand("pstatus").setExecutor(new Reporter());
		this.getCommand("psetradius").setExecutor(new RadiusSetter());
		this.getCommand("psetduration").setExecutor(new DurationSetter());
		this.getCommand("psetamplifier").setExecutor(new AmplifierSetter());
		this.getCommand("ptogglemobs").setExecutor(new MobToggler());
		
		// Register scheduler that checks for distance violations every in-game tick (0.05s)
		BukkitScheduler scheduler = getServer().getScheduler();
		
		// Scheduler runs the run method every tick if a pandemic is enabled
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(enabled) {
					
					// Generate potion effect for current tick
					PotionEffect virus = new PotionEffect(PotionEffectType.WITHER, duration * 20, amplifier);
					
					// Iterate through list of online players (not in SPECTATOR mode) to check for distance violations
					for(Player p1 : Bukkit.getOnlinePlayers()) {
						
						if(!(p1.getGameMode() == GameMode.SPECTATOR)) {
							
							// Iterate through entities within a cube with length radius*2 around p1 (player 1)
							List<Entity> near = p1.getNearbyEntities(radius,radius,radius);
							for(Entity e : near) {
								
								/*
								 * If e is a player not in SPECTATOR mode, calculate distance squared between p1 and e (casted to p2).
								 * If distance squared < radius^2, give both players virus potion effect.
								 */
								if(e instanceof Player) {
									Player p2 = (Player) e;
									
									Location p1L = p1.getLocation();
									Location p2L = p2.getLocation();
									
									if(!(p2.getGameMode() == GameMode.SPECTATOR) && (p1L.distanceSquared(p2L) < (radius * radius))) {
										p1.addPotionEffect(virus);
										p2.addPotionEffect(virus);
									}	
									
								/*
								 * Else if e is a non-player mob, and mobs are infected, calculate distance squared between 
								 * p1 and e. If distance squared < radius^2, give p1 virus potion effect.
								 */
								} else if(e instanceof Mob && mobsInfected) {
									Location p1L = p1.getLocation();
									Location eL = e.getLocation();
									
									if(p1L.distanceSquared(eL) < (radius * radius)) {
										p1.addPotionEffect(virus);
									}		
								}
							
							}
						}
					
					}
				}
			}
		}, 0L, 1L);
	}
}
