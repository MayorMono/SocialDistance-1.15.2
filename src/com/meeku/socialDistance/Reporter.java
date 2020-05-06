package com.meeku.socialDistance;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reporter implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg3.length == 0) {
			if(Main.enabled && arg0.isOp()) {
				Bukkit.broadcastMessage("There is currently a pandemic. "
						+ "The danger radius is " + Main.radius + 
						". The infection period is " + Main.duration + " seconds."
								+ " The amplifier is " + Main.amplifier);
				return true;
			} else if(arg0.isOp()) {
				Bukkit.broadcastMessage("No pandemic is occuring");
				return true;
			}
		}
		
		return false;
	}

}
