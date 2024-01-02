package gg.quartzdev.qlodestones;

import gg.quartzdev.qlodestones.listeners.CompassEventListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class qLodestones extends JavaPlugin {

    private final int BSTATS_PLUGIN_ID = 20606;

    private static qLodestones instance;

    public static qLodestones getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
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
        Bukkit.getPluginManager().registerEvents(new CompassEventListener(), this);
    }


}
