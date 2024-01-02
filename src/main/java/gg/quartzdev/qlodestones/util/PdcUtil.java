package gg.quartzdev.qlodestones.util;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class PdcUtil {

    public static @NotNull Set<Location> getStoredLocations(NamespacedKey key, ItemStack compass){
        CompassMeta compassMeta = (CompassMeta) compass.getItemMeta();
        Set<Location> storedLocations = compassMeta.getPersistentDataContainer()
                .get(key, DataType.asSet(DataType.LOCATION));
        if(storedLocations == null){
            storedLocations = new HashSet<>();
        }
        return storedLocations;
    }

    public static void saveLocation(NamespacedKey key, ItemStack compass, Location location){
        CompassMeta compassMeta = (CompassMeta) compass.getItemMeta();
        Set<Location> storedLocations = compassMeta.getPersistentDataContainer()
                .get(key, DataType.asSet(DataType.LOCATION));
        if(storedLocations == null){
            storedLocations = new HashSet<>();
        }
        storedLocations.add(location);
        compass.setItemMeta(compassMeta);

    }

    public static void setLocationInUI(NamespacedKey key, ItemStack item, Location location){
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, DataType.LOCATION, location);
        item.setItemMeta(itemMeta);
    }

    public static Location getLocationFromUI(NamespacedKey key, ItemStack clickedItem){
        ItemMeta itemMeta = clickedItem.getItemMeta();
        Location location = itemMeta.getPersistentDataContainer().get(key, DataType.LOCATION);
        return location;
    }

}
