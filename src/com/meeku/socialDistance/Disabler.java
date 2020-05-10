package com.meeku.socialdistance;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Disabler implements CommandExecutor {
	
	// Disables pandemic
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg3.length == 0) {
			if(SocialDistance.enabled && arg0.isOp()) {	
				SocialDistance.enabled = false;
				Bukkit.broadcastMessage("The pandemic has ended");
				return true;
			}
			return true;
		}
		return false;
	}
}
