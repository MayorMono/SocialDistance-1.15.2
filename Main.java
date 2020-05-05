package com.meeku.socialDistance;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


public class Main extends JavaPlugin {
	public static boolean enabled = false;
	public static PotionEffect cVirus = new PotionEffect(PotionEffectType.WITHER, 100, 2);
	
	@Override
	public void onEnable() {
		this.getCommand("enable").setExecutor(new Enabler());
		this.getCommand("disable").setExecutor(new Disabler());
		this.getCommand("status").setExecutor(new Reporter());
		
		BukkitScheduler scheduler = getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if(enabled) {
					for(Player p1 : Bukkit.getOnlinePlayers()) {
						List<Entity> near = p1.getNearbyEntities(2,2,2);
						for(Entity e : near) {
							if(e instanceof Player) {
								Player p2 = (Player) e;
								p1.addPotionEffect(cVirus);
								p2.addPotionEffect(cVirus);
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
