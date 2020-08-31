package me.glowinggolem.sorcererpins;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.glowinggolem.sorcererpins.utils.Utils;

public class PinPack {

	private List<Pin> pins;
	private ItemStack pinPack;
	private String name;
	private Random r = new Random();
	
	public PinPack(Material material, int damage, String name, List<String> loreList, List<Pin> pins) {
		this.pins = pins;
		this.name = name;
		
		//create item with damage value
		pinPack = new ItemStack(material, 1, (short) damage);

		//set display name
		ItemMeta meta = pinPack.getItemMeta();
		meta.setDisplayName(Utils.chat("&3Sorcerer Pack - "+name));

		//set lore
		List<String> lore = new ArrayList<String>();
		lore.add(Utils.chat("&e&m-------------------------"));
		lore.add(Utils.chat(""));
		for(String s : loreList) 
			lore.add(Utils.chat(s));
		lore.add(Utils.chat(""));
		lore.add(Utils.chat("&e&m-------------------------"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		//set as unbreakable
		meta.setUnbreakable(true);
		pinPack.setItemMeta(meta);
	}
	
	public List<Pin> getPins() {
		return pins;
	}
	
	public Pin getRandom() {
		int rand  = r.nextInt(pins.size());
		return pins.get(rand);
	}
	
	public ItemStack getItem() {
		return pinPack;
	}
	
	public String getName() {
		return name;
	}
	
}
