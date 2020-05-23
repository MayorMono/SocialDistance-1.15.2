package com.meeku.socialdistance;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Enabler implements CommandExecutor {
	
	// Enables pandemic
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg3.length == 0) {
			if(!SocialDistance.enabled && arg0.isOp()) {
				SocialDistance.enabled = true;
				Bukkit.broadcastMessage("There is now a global pandemic. Practice social distancing!");
				return true;
			}
			return true;
		}
		return false;
	}
}
