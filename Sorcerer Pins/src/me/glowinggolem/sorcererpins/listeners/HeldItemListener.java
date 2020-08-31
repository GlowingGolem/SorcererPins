package me.glowinggolem.sorcererpins.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.glowinggolem.sorcererpins.Main;
import me.glowinggolem.sorcererpins.Pin;
import me.glowinggolem.sorcererpins.PinPack;
import me.glowinggolem.sorcererpins.utils.Utils;

public class HeldItemListener implements Listener {

	private Main plugin;
	private Random r = new Random();
	private int packs;

	public HeldItemListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		packs = plugin.getConfig().getInt("pinsperpack");
	}

	@EventHandler
	public void heldItem(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action a = e.getAction();
		if ((a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) && e.getItem() != null) {
			for (PinPack p : plugin.getPinManager().getPinPacks().values()) {
				if (e.getItem().isSimilar(p.getItem())) {
					//if player right clicks a pin pack
					
					
					for (int i = 0; i < packs; i++) {
						//give player set amount of pins based on pinsperpack variable set in config
						
						//pin a random pin in the pack
						Pin pin = p.getRandom();
						ItemStack pinItem = pin.getItem();
						
						//add lore
						ItemMeta meta = pinItem.getItemMeta();
						meta.setLore(getLore(pin));
						pinItem.setItemMeta(meta);

						//give player item or drop on ground if inventory full
						if (player.getInventory().firstEmpty() != -1) {
							player.getInventory().addItem(pinItem);
						} else {
							player.getWorld().dropItem(player.getLocation().add(0, 1, 0), pinItem);
						}

						
					}
					if(packs > 0) {
						//send a message saying a pack was opened
						Bukkit.broadcastMessage(Utils.chat("&a&lSorcerer Pins&7: &eYou opened your pin pack, your winnings have been placed in your inventory!"));
						player.getInventory().getItemInMainHand()
						.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
					} else {
						Bukkit.broadcastMessage(Utils.chat("&a&lSorcerer Pins&7: &ePins per Pack value is set to "+packs+"&e. You won't get anything from opening this pack"));
					}
				}
			}
		}
	}

	private List<String> getLore(Pin p) {
		//generate random values to get rarity and condition
		int rand = r.nextInt(100);
		String rarity = getRarity(rand);
		String condition = getCondition(rand);

		//set lore to display rarity, condition and pack
		List<String> lore = new ArrayList<String>();
		lore.add(Utils.chat("&e&m--------------------"));
		lore.add(Utils.chat(""));
		lore.add(Utils.chat("&3Pin Rarity: " + rarity));
		lore.add(Utils.chat("&3Pin Condition: &e" + condition));
		lore.add(Utils.chat("&3Pin Pack: " + p.getPackName()));
		lore.add(Utils.chat(""));
		lore.add(Utils.chat("&e&m--------------------"));
		return lore;
	}

	private String getRarity(int rand) {
		/*
		 * Common: 50
		 * Uncommon: 30
		 * Rare: 15
		 * Epic: 4
		 * Legendary: 1
		 */
		String rarity;
		if (rand < 50) {
			rarity = "&cCommon";
		} else if (rand < 80) {
			rarity = "&aUncommon";
		} else if (rand < 95) {
			rarity = "&3Rare";
		} else if (rand < 99) {
			rarity = "&5Epic";
		} else {
			rarity = "&6Legendary";
		}
		return rarity;
	}

	private String getCondition(int rand) {
		/*
		 * Trash: 50
		 * Poor: 30
		 * Good: 15
		 * Excellent: 4
		 * Brand New: 1
		 */
		String condition;
		if (rand < 50) {
			condition = "Trash";
		} else if (rand < 80) {
			condition = "Poor";
		} else if (rand < 95) {
			condition = "Good";
		} else if (rand < 99) {
			condition = "Excellent";
		} else {
			condition = "Brand New";
		}
		return condition;
	}

}
