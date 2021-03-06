package ml.minelounge.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public static Main plugin;
	
	@Override	
	public void onEnable(){
		plugin = this;
		
		getServer().getPluginManager().registerEvents(this, this);
		
		getCommand("staff").setExecutor(new StaffCommand());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "]" + " " + ChatColor.GRAY + player.getName());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		event.setQuitMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "-" + ChatColor.DARK_GRAY + "]" + " " + ChatColor.GRAY + player.getName());
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event){
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		if (inventory.getItemInHand().equals(StaffCommand.TELEPORTER)){
			//Speler klikt op teleporter -> menu openen
			TeleporterMenu.open(player);
		} else if (inventory.getItemInHand().equals(StaffCommand.VANISH_ON)) {
			for (Player online : Bukkit.getOnlinePlayers())
				online.showPlayer(player);
			inventory.setItem(1, StaffCommand.VANISH_OFF);
		} else if (inventory.getItemInHand().equals(StaffCommand.VANISH_OFF)) {
			for (Player online : Bukkit.getOnlinePlayers())
				online.hidePlayer(player);
			inventory.setItem(1, StaffCommand.VANISH_ON);
		}
	}
}
