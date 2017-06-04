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

	private static IconMenu menu = new IconMenu("Class menu", 1 * 9, new IconMenu.OptionClickEventHandler() {

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
		fillMenu();
		menu.open(player);
	}

	private static void fillMenu() {
		int index = 0;
		for (Player player : Bukkit.getOnlinePlayers()){
			ItemStack item = new ItemBuilder(player.getName()).create();
			menu.setOption(index, item, ChatColor.GRAY + player.getName());
		    index++;
		}
	}
}