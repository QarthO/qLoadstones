package gg.quartzdev.qlodestones.inventory;

import gg.quartzdev.qlodestones.qLodestones;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public abstract class CompassHolder implements InventoryHolder {

    qLodestones plugin;
    Inventory inventory;

    public CompassHolder(){
        this.plugin = qLodestones.getInstance();
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public abstract void onClick(InventoryClickEvent event);

    public void createInventory(int size, String title){
        Component component = Component.text(title);
        this.inventory = Bukkit.createInventory(this, size, component);
    }
}
