package ml.minelounge.core;

import java.io.File;

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
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		Player player = (Player) sender;
		File file = new File(Main.plugin.getDataFolder() + "/staffinv", player.getUniqueId() + ".yml");
		
		if(file.exists()){
			player.setAllowFlight(false);
			player.setFlying(false);
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			InvUtils.restoreInventory(file, player.getInventory());
			return true;
		} else {
			player.setAllowFlight(true);
			player.setFlying(true);
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 255, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255, false, false));
			InvUtils.saveIntentory(file, player.getInventory());;
			InvUtils.clearInventory(player.getInventory());
			ItemStack item = new ItemBuilder(Material.COMPASS).setName(ChatColor.AQUA + "Player Teleporter").setLore(ChatColor.GRAY + "Teleport to players around the server").create();
			player.getInventory().addItem(item);
			return true;
		}
	}
}
