package me.glowinggolem.sorcererpins.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;

import me.glowinggolem.sorcererpins.Main;
import me.glowinggolem.sorcererpins.Pin;
import me.glowinggolem.sorcererpins.PinPack;

public class PinManager {

	private HashMap<String, PinPack> pinPacks = new HashMap<String, PinPack>();
		
	public PinManager(Main plugin) {
		
		//Get all values out of config and put into hashmap
		for(String s : plugin.getConfig().getConfigurationSection("packs").getKeys(false)) {
			String name = plugin.getConfig().getString("packs."+s+".name");
			List<String> lore = plugin.getConfig().getStringList("packs."+s+".lore");
			Material item = Material.matchMaterial(plugin.getConfig().getString("packs."+s+".item"));
			int damage = plugin.getConfig().getInt("packs."+s+".damage");
			List<Pin> pins = new ArrayList<Pin>();
			for(String p : plugin.getConfig().getConfigurationSection("packs."+s+".pins").getKeys(false)) {
				String pinName = plugin.getConfig().getString("packs."+s+".pins."+p+".name");
				Material pinItem = Material.matchMaterial(plugin.getConfig().getString("packs."+s+".pins."+p+".item"));
				int pinDamage = plugin.getConfig().getInt("packs."+s+".pins."+p+".damage");
				pins.add(new Pin(pinItem,pinDamage,pinName,name));
			}
			pinPacks.put(s,new PinPack(item,damage,name,lore,pins));
		}
		
		
		
	}
	
	public HashMap<String,PinPack> getPinPacks(){
		return pinPacks;
	}
	
}
