package gg.quartzdev.qlodestones.inventory;

import gg.quartzdev.qlodestones.util.PdcUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CompassUI extends CompassHolder {

    NamespacedKey key;

    public CompassUI(NamespacedKey key, Player player, ItemStack compass){
        super();
        this.key = key;
        this.createInventory(9, "Saved Lodestones");
        this.fill(compass);
        player.openInventory(this.getInventory());
    }

    private ItemStack createItem(Location location){
        ItemStack item = new ItemStack(Material.LODESTONE);
        ItemMeta itemMeta = item.getItemMeta();
        Component name = Component.text("Name");
        itemMeta.displayName(name);
        List<Component> lore = new ArrayList<>();
        Component coords = Component.text(location.blockX() + ", " + location.blockY() + ", " + location.blockZ());
        lore.add(coords);
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    private void fill(ItemStack compass){
        List<Location> storedLocations = PdcUtil.getLocations(this.key, (CompassMeta) compass.getItemMeta());
        for(Location location : storedLocations){
            ItemStack item = this.createItem(location);
            PdcUtil.setItemLocation(this.key, item, location);
            this.getInventory().addItem(item);
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        ItemStack item = event.getCurrentItem();
        if(item == null){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack compass = player.getInventory().getItemInMainHand();
        if(compass.getType() != Material.COMPASS){
            compass = player.getInventory().getItemInOffHand();
            if(compass.getType() != Material.COMPASS){
                player.sendMessage("error");
                return;
            }
        }

        Location clickedStoredLocation = PdcUtil.getItemLocation(this.key, item);

        CompassMeta compassMeta = (CompassMeta) compass.getItemMeta();
        compassMeta.setLodestone(clickedStoredLocation);
        compass.setItemMeta(compassMeta);
    }
}
