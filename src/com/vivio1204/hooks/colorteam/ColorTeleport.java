package com.vivio1204.hooks.colorteam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.bukkit.plugin.java.JavaPlugin;

public class ColorTeleport extends JavaPlugin{
	public static int reach = 10;
	public static Properties sample = new Properties();
	
	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new ColorListener(), this);
		getCommand("colorteleport").setExecutor(new ColorCommand());
		Properties prop = new Properties();
		FileOutputStream filestream = null;
		OutputStreamWriter outwriter = null;
		try{
			File directory = new File("plugins/" + getName());
			if(!directory.exists()){
				directory.mkdir();
			}
			File masterconfig = new File("plugins/" + getName() + "/plugin.properties");
			if(!masterconfig.exists()){
				filestream = new FileOutputStream(masterconfig);
				outwriter = new OutputStreamWriter(filestream, "UTF-8");
				sample.store(outwriter, getName() + "config file.");
			}
			try{
				prop.load(new InputStreamReader(new FileInputStream(masterconfig), "UTF-8"));
			}catch(FileNotFoundException e){
				throw new IOException();
			}
		}catch(IOException e){
			e.printStackTrace();
			setEnabled(false);
		}finally{
			try{
				filestream.close();
				outwriter.close();
			}catch(IOException e){
			}
		}
		reach = Integer.valueOf(prop.getProperty("KillTeleport"));
	}
	
	static{
		sample.clear();
		sample.put("KillTeleport", "10");
	}
}