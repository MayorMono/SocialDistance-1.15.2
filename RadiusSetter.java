package com.meeku.socialDistance;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RadiusSetter implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			try {
				if(!(arg3.length == 1)) {
					return false;
				}
				
				int radius = Integer.parseInt(arg3[0]);
				
				if(radius > 0 && radius <= 10) {
					Main.radius = radius;
					Bukkit.broadcastMessage("Danger radius is now " + arg3[0]);
				}
			} catch(NumberFormatException e) {
				return false;
			} catch(IndexOutOfBoundsException e) {
				return false;
			}
		}
		return true;
	}

}
