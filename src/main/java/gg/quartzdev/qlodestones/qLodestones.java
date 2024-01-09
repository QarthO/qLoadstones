package gg.quartzdev.qlodestones;

import gg.quartzdev.qlodestones.listeners.CompassEventListener;
import gg.quartzdev.qlodestones.storage.Confiq;
import gg.quartzdev.qlodestones.util.Loqqer;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class qLodestones extends JavaPlugin {

    private final int BSTATS_PLUGIN_ID = 20606;
    private static qLodestones instance;
    public Confiq config;
    public Loqqer logger;

    public static qLodestones getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.logger = new Loqqer();
        this.config = new Confiq();

        this.setupMetrics();
        this.registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupMetrics(){
        Metrics metrics = new Metrics(this, BSTATS_PLUGIN_ID);
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new CompassEventListener(), this);
    }


}
