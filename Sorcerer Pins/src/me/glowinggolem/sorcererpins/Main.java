package me.glowinggolem.sorcererpins;

import org.bukkit.plugin.java.JavaPlugin;

import me.glowinggolem.sorcererpins.commands.PinPackCommand;
import me.glowinggolem.sorcererpins.listeners.HeldItemListener;
import me.glowinggolem.sorcererpins.managers.PinManager;

public class Main extends JavaPlugin {

	private PinManager pinManager;

	@Override
	public void onEnable() {
		saveDefaultConfig();

		pinManager = new PinManager(this);
		
		new HeldItemListener(this);
		new PinPackCommand(this);
	}

	@Override
	public void onDisable() {

	}

	public PinManager getPinManager() {
		return pinManager;
	}

}
