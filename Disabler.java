package com.meeku.socialDistance;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Disabler implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(Main.enabled && arg0.isOp()) {
			Main.enabled = false;
			Bukkit.broadcastMessage("The pandemic has ended");
		}
		return true;
	}
}
