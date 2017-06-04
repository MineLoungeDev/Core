package xyz.derkades.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InvUtils {
	
	public static void saveIntentory(File file, PlayerInventory inventory){
		YamlConfiguration config = new YamlConfiguration();
		config.set("inventory.armor", inventory.getArmorContents());
		config.set("inventory.content", inventory.getContents());
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void restoreInventory(File file, PlayerInventory inventory){
        YamlConfiguration cconfig = YamlConfiguration.loadConfiguration(file);
        ItemStack[] content = cconfig.getList("inventory.armor").toArray(new ItemStack[]{});
        inventory.setArmorContents(content);
        content = cconfig.getList("inventory.content").toArray(new ItemStack[]{});
        inventory.setContents(content);
        file.delete();
    }

}
