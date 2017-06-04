package ml.minelounge.core;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import xyz.derkades.utils.InvUtils;
import xyz.derkades.utils.ItemBuilder;

public class StaffCommand implements CommandExecutor {
	
	public static final ItemStack TELEPORTER = new ItemBuilder(Material.COMPASS).setName(ChatColor.AQUA + "Player Teleporter").setLore(ChatColor.GRAY + "Teleport to players around the server").create();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		Player player = (Player) sender;
		File file = new File(Main.plugin.getDataFolder() + "/staffinv", player.getUniqueId() + ".yml");
		
		if(file.exists()){
			player.setAllowFlight(false);
			player.setFlying(false);
			
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

			InvUtils.restoreInventory(file, player.getInventory());
			
			for (Player online : Bukkit.getOnlinePlayers())
				online.showPlayer(player);
			
			return true;
		} else {
			player.setAllowFlight(true);
			player.setFlying(true);
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 255, false, false));

			InvUtils.saveIntentory(file, player.getInventory());
			InvUtils.clearInventory(player.getInventory());
			
			player.getInventory().addItem(TELEPORTER);
			
			for (Player online : Bukkit.getOnlinePlayers())
				online.hidePlayer(player);
			
			return true;
		}
	}
}
