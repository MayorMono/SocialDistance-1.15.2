package com.meeku.socialdistance;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reporter implements CommandExecutor {
	
	// Reports status of pandemic
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg3.length == 0) {
			
			arg0.sendMessage("--------------------------------");
			
			if(SocialDistance.enabled) {
				arg0.sendMessage("There is currently a pandemic");
			} else {
				arg0.sendMessage("No pandemic is occuring");
			}
			
			arg0.sendMessage("Mobs infected: " + SocialDistance.mobsInfected);
			arg0.sendMessage("Danger distance: " + SocialDistance.radius);
			arg0.sendMessage("Infection period: " + SocialDistance.duration + " second(s)");
			arg0.sendMessage("Amplifier: " + SocialDistance.amplifier);
			arg0.sendMessage("--------------------------------");
				
			return true;
		}
		
		return false;
	}

}
