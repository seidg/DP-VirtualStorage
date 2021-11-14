package com.darksoldier1404.dvs;

import com.darksoldier1404.dvs.commands.DVSCommand;
import com.darksoldier1404.dvs.utils.ConfigUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VirtualStorage extends JavaPlugin {
    private static VirtualStorage plugin;
    public String prefix;
    public YamlConfiguration config;
    public Map<UUID, YamlConfiguration> udata = new HashMap<>();

    public static VirtualStorage getInstance() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;
        getLogger().info("VirtualStorage has been enabled!");
        ConfigUtils.loadDefaultConfig();
        getCommand("창고").setExecutor(new DVSCommand());
    }
}
