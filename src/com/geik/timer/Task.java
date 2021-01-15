package com.geik.timer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class Task {
	@SuppressWarnings("unused")
	private Main plugin;

	public Task(Main plugin) {
		this.plugin = plugin;
	}

	private static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat formatx = new SimpleDateFormat("EEE");
	static int k = 0;

	public static boolean hasTime() {
		File c = new File("plugins/TaskScheduler/config.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(c);
		for (String s : cfg.getConfigurationSection("Tasks").getKeys(false)) {
			if (Task.format.format(new Date()).equals(cfg.getString("Tasks." + s + ".Time"))) {
				if (cfg.getBoolean("Tasks." + s + ".HaveDate")) {
					if (Task.formatx.format(new Date()).equals(cfg.getString("Tasks." + s + ".Day"))) {
						k = Integer.valueOf(s);
						return true;
					}
				} else {
					k = Integer.valueOf(s);
					return true;
				}
			}
		}
		return false;
	}

	public static BukkitRunnable taskid;

	public static void taskAgain() {
		if (taskid != null) {
			taskid.cancel();
			taskid = null;
		}
		taskid = new BukkitRunnable() {
			@Override
			public synchronized void cancel() throws IllegalStateException {
				super.cancel();
			}

			public void run() {
				if (Task.hasTime()) {
					runix();
					taskid.cancel();
				}
			}
		};
		taskid.runTaskTimer(Main.instance, 1L, 1L);
	}

	public static void runix() {
		File c = new File("plugins/TaskScheduler/config.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(c);
		for (String lootboxx : cfg.getStringList("Tasks." + k + ".Command")) {
			lootboxx = lootboxx.replaceAll("&", "§");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), lootboxx);
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				taskAgain();
			}
		}, 25L);
	}
}
