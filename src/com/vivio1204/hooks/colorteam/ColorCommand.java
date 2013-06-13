package com.vivio1204.hooks.colorteam;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ColorCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("colorteleport")){
			if(args.length > 0){
				int reaching;
				try{
					reaching =  Integer.valueOf(args[0]);
				}catch(NumberFormatException exception){
					sender.sendMessage(ChatColor.AQUA + "[ColorTeleport]不正な数字です");
					return true;
				}
				ColorTeleport.reach = reaching;
				sender.sendMessage(ChatColor.AQUA + "[ColorTeleport]" + ColorTeleport.reach + "に設定しました");
			}else{
				sender.sendMessage(ChatColor.AQUA + "[ColorTeleport]" + "引数が足りません");
			}
		}
		return true;
	}
}
