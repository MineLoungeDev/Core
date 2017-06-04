package ml.minelounge.core;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void JoinMessage(PlayerJoinEvent event){
		event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "]");
	}
	
	@EventHandler
	public void QuitMessage(PlayerQuitEvent event){
		event.setQuitMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "-" + ChatColor.DARK_GRAY + "]");
	}
}
