package gg.quartzdev.qlodestones.listeners;

import gg.quartzdev.qlodestones.inventory.CompassHolder;
import gg.quartzdev.qlodestones.inventory.CompassUI;
import gg.quartzdev.qlodestones.qLodestones;
import gg.quartzdev.qlodestones.util.Messages;
import gg.quartzdev.qlodestones.util.Sender;
import gg.quartzdev.qlodestones.util.PdcUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
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

import java.util.ArrayList;
import java.util.List;

public class CompassEventListener implements Listener{
    qLodestones plugin;
    NamespacedKey key;
    String PDC_KEY = "lodestones";
    int MAX_LOCATIONS = 9;

    public CompassEventListener(){
        this.plugin = qLodestones.getInstance();
        this.key = new NamespacedKey(this.plugin, PDC_KEY);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
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
        if(!isUsingCompass(compass)){
            return;
        }

        Player player = event.getPlayer();
        Block lodestone = event.getClickedBlock();

//        Trying to open compass
        if(lodestone == null || lodestone.getType() != Material.LODESTONE){
            this.openCompassUI(player, compass);
            return;
        }

        CompassMeta compassMeta = (CompassMeta) compass.getItemMeta();
        if(!this.addLodestone(lodestone, compassMeta)){
            Sender.message(player, Messages.ERROR_LODESTONE_ADD);
            return;
        }

        Sender.message(player, Messages.LODESTONE_ADD.parse("location", Sender.location(lodestone.getLocation())));
        compass.setItemMeta(compassMeta);

    }

    private boolean isUsingCompass(ItemStack compass){
        if(compass == null || compass.getType() != Material.COMPASS){
            return false;
        }
        return true;

    }

    private boolean addLodestone(Block lodestone, CompassMeta compassMeta){

//        Get existing lodestone locations
        List<Location> storedLocations = PdcUtil.getLocations(this.key, compassMeta);
        Location location = lodestone.getLocation();

//        Check if lodestone location already added
        if(storedLocations.contains(location)){
            return false;
        }

//        Check if max locations reached
        if(storedLocations.size() >= this.MAX_LOCATIONS){
            return false;
        }

//        Add lodestone
        storedLocations.add(lodestone.getLocation());
        PdcUtil.updateLocations(this.key, compassMeta, storedLocations);

//        Update lore on compass
        this.updateLore(compassMeta, storedLocations, location);
        return true;
    }

    public void openCompassUI(Player player, ItemStack compass){
        new CompassUI(this.key, player, compass);
    }

    public void updateLore(CompassMeta compassMeta, List<Location> storedLocations, Location location){
        List<Component> lore = new ArrayList<>();
        Component component = Component.text("Locations Save: " + storedLocations.size() + "/" + this.MAX_LOCATIONS).decoration(TextDecoration.ITALIC, false);
        lore.add(component);
        component = Component.text("Tracking: (" + location.blockX() + ", " + location.blockY() + ", " + location.blockZ() + ")").decoration(TextDecoration.ITALIC, false);
        lore.add(component);
        compassMeta.lore(lore);
    }

}
