package me.glowinggolem.sorcererpins;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.glowinggolem.sorcererpins.utils.Utils;

public class Pin {

	private ItemStack pin;
	private String packName;

	public Pin(Material material, int damage, String name, String packName) {
		this.packName = packName;
		
		// create item with a damage value
		pin = new ItemStack(material, 1, (short) damage);

		//set name (lore is set later)
		ItemMeta meta = pin.getItemMeta();
		meta.setDisplayName(Utils.chat(name));
		
		//set as unbreakable
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		pin.setItemMeta(meta);

	}

	public ItemStack getItem() {
		return pin;
	}

	public String getPackName() {
		return packName;
	}

	
	

}
