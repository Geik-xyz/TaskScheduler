package com.geik.timer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{
	
	private Main plugin;
	  protected Commands(Main plugin) {
	    this.plugin = plugin;}
	  
	  
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		  if (label.equalsIgnoreCase("taskscheduler")){
			  if (sender instanceof Player){
				  Player player = (Player) sender;
				  if (player.hasPermission("task.scheduler")){
					  plugin.reloadConfig();
					  player.sendMessage(Main.color("&2&lScheduler &aConfig has loaded successfully."));
					  Task.taskid.cancel();
					  Task.taskAgain();
				  }
				  else {
					  player.sendMessage(Main.color("&2&lScheduler &cYou do not have enought perm for execute this command."));
				  }
			  }
			  else {
				  plugin.reloadConfig();
				  sender.sendMessage(Main.color("&2&lScheduler &aConfig has loaded successfully."));  
				  Task.taskid.cancel();
				  Task.taskAgain();}
		  }
	  
	  return false;
	  }
}
