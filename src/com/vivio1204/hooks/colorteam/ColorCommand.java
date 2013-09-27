package com.vivio1204.hooks.colorteam;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.ucchyocean.ct.ColorTeaming;

public class ColorCommand implements CommandExecutor{
	
	private ColorTeaming ct;
	
	public ColorCommand(ColorTeaming ct) {
		this.ct = ct;
	}
	
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
				ct.getCTConfig().setKillTrophy(reaching);
				ct.getCTConfig().saveConfig();
				sender.sendMessage(ChatColor.AQUA + "[ColorTeleport]" + reaching + "に設定しました");
			}else{
				sender.sendMessage(ChatColor.AQUA + "[ColorTeleport]" + "引数が足りません");
			}
		}
		return true;
	}
}
