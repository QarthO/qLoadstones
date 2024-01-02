package gg.quartzdev.qlodestones.listeners;

import com.jeff_media.morepersistentdatatypes.DataType;
import gg.quartzdev.qlodestones.inventory.CompassHolder;
import gg.quartzdev.qlodestones.inventory.CompassUI;
import gg.quartzdev.qlodestones.qLodestones;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.HashSet;
import java.util.Set;

public class CompassEventListener implements Listener{
    qLodestones plugin;
    NamespacedKey key;
    String PDC_KEY = "lodestones";

    public CompassEventListener(){
        this.plugin = qLodestones.getInstance();
        this.key = new NamespacedKey(this.plugin, PDC_KEY);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!(event.getInventory().getHolder() instanceof CompassHolder)){
            return;
        }
        ((CompassHolder) event.getInventory().getHolder()).onClick(event);
    }

    @EventHandler
    public void onCompassInteract(PlayerInteractEvent event){

        if(event.getAction().isLeftClick()){
            return;
        }

        ItemStack compass = event.getItem();
        Player player = event.getPlayer();
        if(compass == null){
            return;
        }
        if(compass.getType() != Material.COMPASS){
            return;
        }

        Block lodestone = event.getClickedBlock();
        if(lodestone == null || lodestone.getType() != Material.LODESTONE){
            this.openCompassUI(player, compass);
            return;
        }
        this.addLodestoneLocation(compass, lodestone.getLocation());
    }

    public void openCompassUI(Player player, ItemStack compass){
        new CompassUI(this.key, player, compass);
    }

    public void addLodestoneLocation(ItemStack compass, Location location){
        CompassMeta compassMeta = (CompassMeta) compass.getItemMeta();
        PersistentDataContainer pdc = compassMeta.getPersistentDataContainer();
        Set<Location> storedLocations =  pdc.get(this.key, DataType.asSet(DataType.LOCATION));
        if(storedLocations == null){
            storedLocations = new HashSet<>();
        }
        storedLocations.add(location);
        pdc.set(this.key, DataType.asSet(DataType.LOCATION), storedLocations);
        compass.setItemMeta(compassMeta);
    }

}
