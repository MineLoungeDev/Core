package ml.minelounge.core;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
	public static final ItemStack VANISH_ON = new ItemBuilder(Material.INK_SACK).setName(ChatColor.GREEN + "Vanish On").setLore(ChatColor.GRAY + "Toggle your vanish on/off").setDamage(8).create();
	public static final ItemStack VANISH_OFF = new ItemBuilder(Material.INK_SACK).setName(ChatColor.GRAY + "Vanish Off").setLore(ChatColor.GRAY + "Toggle your vanish on/off").setDamage(10).create();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		Player player = (Player) sender;
		
		if (!player.hasPermission("ml.staff")){
			player.sendMessage("You don't have permission to use this command!");
			return true;
		}
		
		File file = new File(Main.plugin.getDataFolder() + "/staffinv", player.getUniqueId() + ".yml");
		
		if (file.exists()){
			
			if (player.getGameMode() != GameMode.CREATIVE){
				player.setAllowFlight(false);
				player.setFlying(false);
			}
					
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
			
			player.getInventory().setItem(0, TELEPORTER);
			player.getInventory().setItem(1, VANISH_ON);
			
			for (Player online : Bukkit.getOnlinePlayers())
					online.hidePlayer(player);
			
			return true;
		}
	}
}
