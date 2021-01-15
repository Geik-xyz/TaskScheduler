package com.geik.timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	
	public static Main instance;
	
	public void onEnable() {
		instance = this;
		getCommand("taskscheduler").setExecutor(new Commands(this));
		saveDefaultConfig();
		Task.taskAgain();
		Bukkit.getConsoleSender().sendMessage(color("&6&lTaskScheduler &aLoaded! Version: 1.0"));
			Bukkit.getConsoleSender().sendMessage(color("&6&lTaskScheduler &aMade by Geik."));
	}
    
    public static String color(String yazirengi){return ChatColor.translateAlternateColorCodes('&', yazirengi);}
}
