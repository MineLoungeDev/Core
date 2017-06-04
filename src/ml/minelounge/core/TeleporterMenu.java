package ml.minelounge.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import xyz.derkades.utils.IconMenu;
import xyz.derkades.utils.IconMenu.OptionClickEvent;
import xyz.derkades.utils.ItemBuilder;

public class TeleporterMenu {

	private static IconMenu menu = new IconMenu(ChatColor.AQUA + "Player Teleporter", 1 * 9, new IconMenu.OptionClickEventHandler() {

		@Override
		public void onOptionClick(OptionClickEvent event) {
			ItemStack clickedItem = event.getItem();
			String skullOwner = ((SkullMeta) clickedItem.getItemMeta()).getOwner();
			Player target = Bukkit.getPlayerExact(skullOwner);
			Player player = event.getPlayer();
			player.teleport(target);
		}
	});

	public static void open(Player player) {
		int index = 0;
		for (Player online : Bukkit.getOnlinePlayers()){
			if (online.getName().equals(player.getName()))
				continue;
			
			ItemStack item = new ItemBuilder(online.getName()).create();
			menu.setOption(index, item, ChatColor.GRAY + online.getName());
		    index++;
		}
		
		menu.open(player);
	}

}