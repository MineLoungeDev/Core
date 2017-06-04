package ml.minelounge.core;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import xyz.derkades.utils.IconMenu;
import xyz.derkades.utils.IconMenu.OptionClickEvent;

public class TeleporterMenu {

	private static IconMenu menu = new IconMenu("Class menu", 1 * 9, new IconMenu.OptionClickEventHandler() {

		@Override
		public void onOptionClick(OptionClickEvent event) {

		}
	});

	public static void open(Player player) {
		fillMenu();
		menu.open(player);
	}

	private static void fillMenu() {
		ItemStack voorbeeldItem = new ItemStack(Material.STONE);
		menu.setOption(0, voorbeeldItem, "Voorbeeld");
	}

}