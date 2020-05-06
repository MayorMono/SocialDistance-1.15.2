package com.meeku.socialDistance;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AmplifierSetter implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0.isOp()) {
			try {
				if(!(arg3.length == 1)) {
					return false;
				}
				
				int amplifier = Integer.parseInt(arg3[0]);
				
				if(amplifier >= 0 && amplifier <= 5) {
					Main.amplifier = amplifier;
					Bukkit.broadcastMessage("Infection amplifier is now " + arg3[0]);
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
