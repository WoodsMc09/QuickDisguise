package me.woodsmc.quickdisguise;

import org.bukkit.plugin.java.JavaPlugin;

public final class QuickDisguise extends JavaPlugin {

    private DisguiseYML disguiseYML;

    @Override
    public void onEnable() {
        // Plugin startup logic


        //files
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        disguiseYML = new DisguiseYML();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DisguiseYML getDisguiseYML() {
        return disguiseYML;
    }
}
