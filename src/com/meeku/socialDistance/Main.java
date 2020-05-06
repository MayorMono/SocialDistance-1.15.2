package com.meeku.socialDistance;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;


public class Main extends JavaPlugin {
	public static boolean enabled = false;
	public static int duration = 5;
	public static int amplifier = 2;
	public static int radius = 2;
	
	@Override
	public void onEnable() {
		this.getCommand("enable").setExecutor(new Enabler());
		this.getCommand("disable").setExecutor(new Disabler());
		this.getCommand("status").setExecutor(new Reporter());
		this.getCommand("setradius").setExecutor(new RadiusSetter());
		this.getCommand("setduration").setExecutor(new DurationSetter());
		this.getCommand("setamplifier").setExecutor(new AmplifierSetter());
		
		BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(enabled) {
					PotionEffect virus = new PotionEffect(PotionEffectType.WITHER, duration * 20, amplifier);
					for(Player p1 : Bukkit.getOnlinePlayers()) {
						List<Entity> near = p1.getNearbyEntities(radius,radius,radius);
						for(Entity e : near) {
							if(e instanceof Player) {
								Player p2 = (Player) e;
								
								Location p1L = p1.getLocation();
								Location p2L = p2.getLocation();
								
								if(p1L.distanceSquared(p2L) < (radius * radius)) {
									p1.addPotionEffect(virus);
									p2.addPotionEffect(virus);
								}	
								
							} else if(e instanceof Mob) {
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
		}, 0L, 1L);
	}
	
	@Override
	public void onDisable() {
		
	}

}
