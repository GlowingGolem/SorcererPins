package me.glowinggolem.sorcererpins.utils;

import org.bukkit.ChatColor;

public class Utils {

	public static String chat(String s) {
		//use & as �
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
