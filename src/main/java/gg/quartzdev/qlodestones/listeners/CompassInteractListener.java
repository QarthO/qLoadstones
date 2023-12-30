package gg.quartzdev.qlodestones.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CompassInteractListener implements Listener{

    @EventHandler
    public void onCompassInteract(PlayerInteractEvent event){

        ItemStack compass = event.getItem();
        Player player = event.getPlayer();
        if(compass == null){
            return;
        }
        if(compass.getType() != Material.COMPASS){
            return;
        }

        Block block = event.getClickedBlock();

        if(block == null || block.getType() != Material.LODESTONE){
            this.openCompassUI(player);
        }

    }

    public void openCompassUI(Player player){

    }

}
