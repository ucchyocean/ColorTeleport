package com.vivio1204.hooks.colorteam;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.ucchyocean.ct.event.ColorTeamingTrophyKillEvent;

public class ColorListener implements Listener{

	private ColorTeleport plugin;
	
	public ColorListener(ColorTeleport plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public  void onReachKillTrophy(ColorTeamingTrophyKillEvent event) {
		 
		Bukkit.broadcastMessage("チーム" + event.getTeam().getDisplayName() + 
				  ChatColor.RESET + "が勝利しました。");
		
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable(){
			@Override
			public void run(){
				for(Player player: Bukkit.getOnlinePlayers()){
					player.setHealth(player.getMaxHealth());
					player.teleport(player.getWorld().getSpawnLocation());
				}
			}
		}, 3);
	}
}
