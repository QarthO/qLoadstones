package gg.quartzdev.qlodestones;

import gg.quartzdev.qlodestones.listeners.CompassInteractListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class qLodestones extends JavaPlugin {

    private final int BSTATS_PLUGIN_ID = 20606;

    @Override
    public void onEnable() {
        this.enableMetrics();
        this.registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void enableMetrics(){
        Metrics metrics = new Metrics(this, BSTATS_PLUGIN_ID);
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new CompassInteractListener(), this);
    }


}
