package me.woodsmc.quickdisguise;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DisguiseYML {

    //create configuration instance
    private FileConfiguration configuration = null;
    //main instance
    private final QuickDisguise pC = QuickDisguise.getPlugin(QuickDisguise.class);
    //create config file
    private File configFile = null;

    public DisguiseYML(){
        //save configuration
        saveDefaultConfig();
    }

    //reload the config
    public void reloadConfig(){
        //check if configFile is null
        if(this.configFile == null){
            //create config file
            this.configFile = new File(this.pC.getDataFolder(), "disguise.yml");
        }
        //set configuration instance to YamlConfiguration
        this.configuration = YamlConfiguration.loadConfiguration(this.configFile);

        //get input stream
        InputStream defaultStream = this.pC.getResource("disguise.yml");
        //check if stream is null
        if(defaultStream != null){
            //create default config instance
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            //set config defaults to defaultConfig
            this.configuration.setDefaults(defaultConfig);
        }
    }

    //get config
    public FileConfiguration getConfig(){
        //check if configuration is null
        if(this.configuration == null){
            //reload it
            reloadConfig();
        }
        //return configuration
        return this.configuration;
    }
    public void saveConfig(){
        //check if configuration and configFile are null
        if(this.configuration == null || this.configFile == null){
            return;
        }

        try {
            //save configFile
            getConfig().save(this.configFile);
        } catch (IOException e) {
            pC.getLogger().log(Level.WARNING, "§cCould not save config to " + this.configFile, e);
        }
    }

    //save default config
    public void saveDefaultConfig() {
        //check if configFile is null
        if (this.configFile == null) {

            //set config file
            this.configFile = new File(pC.getDataFolder(), "disguise.yml");
        }

        //check if configFile exists
        if(!this.configFile.exists()){

            //save config
            pC.saveResource("disguise.yml", false);
        }
    }
}
