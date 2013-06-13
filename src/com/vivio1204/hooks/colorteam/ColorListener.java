package com.vivio1204.hooks.colorteam;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.ucchyocean.ct.ColorTeaming;

public class ColorListener implements Listener{

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(PlayerDeathEvent event){
		if(event.getEntity().getKiller() != null){
			int i = ColorTeaming.killDeathCounts.get(ColorTeaming.getPlayerColor(event.getEntity().getKiller()))[0];
			if(i == ColorTeleport.reach){
				for(Player player: Bukkit.getOnlinePlayers()){
					player.setHealth(player.getMaxHealth());
				}
				event.getEntity().setHealth(event.getEntity().getMaxHealth());
				Bukkit.getScheduler().runTaskLater(ColorTeleport.plugin, new Runnable(){
					@Override
					public void run(){
						for(Player player: Bukkit.getOnlinePlayers()){
							player.teleport(player.getWorld().getSpawnLocation());
						}
					}
				}, 3);
			}
		}
	}
}
