package me.glowinggolem.sorcererpins.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.glowinggolem.sorcererpins.Main;
import me.glowinggolem.sorcererpins.utils.Utils;

public class PinPackCommand implements CommandExecutor {
	
	private Main plugin;

	public PinPackCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("sorcererpins").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		//test if player has permissions
		if (sender.hasPermission("sorcererpins.give")) {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(Utils.chat("&2------Sorcerer Pins Help------"));
					sender.sendMessage(Utils.chat(" &e- give "));
					sender.sendMessage(Utils.chat("&aUsage: /sorcererpins give <player> pinpack <packName> <amount>"));
					sender.sendMessage(Utils.chat("&3Gives the targeted player an number of packs specified by <amount> and <packName>. @p, @a, @r and @s can also be used to target players"));
				}
			} else if (args.length == 4) {
				//amount not specified
				if (args[0].equalsIgnoreCase("give") && args[2].equalsIgnoreCase("pinpack")) {
					if (plugin.getPinManager().getPinPacks().containsKey(args[3].toLowerCase())) {
						Player player = Bukkit.getServer().getPlayerExact(args[1]);
						if (player != null) {
							//if command syntax correct and pack and player exist give player pack
							//if inventory is full drop item on ground
							if (player.getInventory().firstEmpty() != -1) {
								player.getInventory()
										.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
							} else {
								player.getWorld().dropItem(player.getLocation().add(0, 1, 0),
										plugin.getPinManager().getPinPacks().get(args[3]).getItem());
							}

						} else if (args[1].equalsIgnoreCase("@p")) {
							if (sender instanceof Player) {
								Player p = (Player) sender;
								if (p.getInventory().firstEmpty() != -1) {
									p.getInventory()
											.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								} else {
									p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
											plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								}
							} else {
								sender.sendMessage(Utils.chat("&cYou cannot target @p from console"));
							}
						} else if (args[1].equalsIgnoreCase("@r")) {
							Object[] players = Bukkit.getOnlinePlayers().toArray();
							Random rand = new Random();
							int r = rand.nextInt(players.length);
							Player p = (Player) players[r];
							if (p.getInventory().firstEmpty() != -1) {
								p.getInventory().addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
							} else {
								p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
										plugin.getPinManager().getPinPacks().get(args[3]).getItem());
							}
						} else if (args[1].equalsIgnoreCase("@a")) {
							for (Player p : Bukkit.getOnlinePlayers()) {
								if (p.getInventory().firstEmpty() != -1) {
									p.getInventory()
											.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								} else {
									p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
											plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								}
							}
						} else if (args[1].equalsIgnoreCase("@s")) {
							if (sender instanceof Player) {
								Player p = (Player) sender;
								if (p.getInventory().firstEmpty() != -1) {
									p.getInventory()
											.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								} else {
									p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
											plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								}
							} else {
								sender.sendMessage(Utils.chat("&cYou cannot target @s from console"));
							}
						} else {
							sender.sendMessage(Utils.chat("&cThat player doesn't exist"));
						}
					} else {
						sender.sendMessage(Utils.chat("&cThat pin pack doesn't exist"));
					}
				} else {
					sender.sendMessage(Utils.chat("&cUsage: /sorcererpins give <player> pinpack <packName> <amount>"));
				}
	
			} else if (args.length == 5) {
				//amount is specified
				if (args[0].equalsIgnoreCase("give") && args[2].equalsIgnoreCase("pinpack")) {
					if (plugin.getPinManager().getPinPacks().containsKey(args[3].toLowerCase())) {
						Player player = Bukkit.getServer().getPlayerExact(args[1]);
						int amount = 0;
						try {
							amount = Integer.parseInt(args[4]);
						} catch (NumberFormatException e) {
							sender.sendMessage(Utils.chat("&cThe amount must be an integer"));
						}
						if (player != null) {
							
							//loop through for each item
							for (int i = 0; i < amount; i++) {
								//if command syntax correct and pack and player exist give player pack
								//if inventory is full drop item on ground
								if (player.getInventory().firstEmpty() != -1) {
									player.getInventory()
											.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								} else {
									player.getWorld().dropItem(player.getLocation().add(0, 1, 0),
											plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								}
							}
						} else if (args[1].equalsIgnoreCase("@p")) {
							if (sender instanceof Player) {
								Player p = (Player) sender;
								for (int i = 0; i < amount; i++) {
									if (p.getInventory().firstEmpty() != -1) {
										p.getInventory()
												.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
									} else {
										p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
												plugin.getPinManager().getPinPacks().get(args[3]).getItem());
									}
								}
							} else {
								sender.sendMessage(Utils.chat("&cYou cannot target @p from console"));
							}
						} else if (args[1].equalsIgnoreCase("@r")) {
							Object[] players = Bukkit.getOnlinePlayers().toArray();
							Random rand = new Random();
							int r = rand.nextInt(players.length);
							Player p = (Player) players[r];
							for (int i = 0; i < amount; i++) {
								if (p.getInventory().firstEmpty() != -1) {
									p.getInventory()
											.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								} else {
									p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
											plugin.getPinManager().getPinPacks().get(args[3]).getItem());
								}
							}
						} else if (args[1].equalsIgnoreCase("@a")) {
							for (Player p : Bukkit.getOnlinePlayers()) {
								for (int i = 0; i < amount; i++) {
									if (p.getInventory().firstEmpty() != -1) {
										p.getInventory()
												.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
									} else {
										p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
												plugin.getPinManager().getPinPacks().get(args[3]).getItem());
									}
								}
							}
						} else if (args[1].equalsIgnoreCase("@s")) {
							if (sender instanceof Player) {
								Player p = (Player) sender;
								for (int i = 0; i < amount; i++) {
									if (p.getInventory().firstEmpty() != -1) {
										p.getInventory()
												.addItem(plugin.getPinManager().getPinPacks().get(args[3]).getItem());
									} else {
										p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
												plugin.getPinManager().getPinPacks().get(args[3]).getItem());
									}
								}
							} else {
								sender.sendMessage(Utils.chat("&cYou cannot target @s from console"));
							}

						} else {
							sender.sendMessage(Utils.chat("&cThat player doesn't exist"));
						}
					} else {
						sender.sendMessage(Utils.chat("&cThat pin pack doesn't exist"));
					}
				} else {
					sender.sendMessage(Utils.chat("&cUsage: /sorcererpins give <player> pinpack <packName> <amount>"));
				}
			} else {
				sender.sendMessage(Utils.chat("&cUsage: /sorcererpins help"));
			}
		} else {
			sender.sendMessage(Utils.chat("&cYou do not have permission to use this command"));
		}

		return false;
	}
}
