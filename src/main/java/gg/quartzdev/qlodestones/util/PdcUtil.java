package gg.quartzdev.qlodestones.util;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PdcUtil {

    public static @NotNull List<Location> getLocations(NamespacedKey key, CompassMeta compassMeta){
        List<Location> storedLocations = compassMeta.getPersistentDataContainer().get(key, DataType.asList(DataType.LOCATION));
        if(storedLocations == null){
            storedLocations = new ArrayList<>();
        }
        return storedLocations;
    }

    public static void updateLocations(NamespacedKey key, CompassMeta compassMeta, List<Location> storedLocations){
        compassMeta.getPersistentDataContainer().set(key, DataType.asList(DataType.LOCATION), storedLocations);
    }

    public static void setItemLocation(NamespacedKey key, ItemStack item, Location location){
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, DataType.LOCATION, location);
        item.setItemMeta(itemMeta);
    }

    public static Location getItemLocation(NamespacedKey key, ItemStack clickedItem){
        ItemMeta itemMeta = clickedItem.getItemMeta();
        return itemMeta.getPersistentDataContainer().get(key, DataType.LOCATION);
    }

}
