package com.darksoldier1404.dvs;

import com.darksoldier1404.dvs.commands.DVSCommand;
import com.darksoldier1404.dvs.events.DVSEvent;
import com.darksoldier1404.dvs.functions.DVSFunction;
import com.darksoldier1404.dvs.utils.ConfigUtils;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VirtualStorage extends JavaPlugin {
    private static VirtualStorage plugin;
    public String prefix;
    public YamlConfiguration config;
    public Map<UUID, YamlConfiguration> udata = new HashMap<>();
    public Map<UUID, Integer> currentInventory = new HashMap<>();
    public Essentials ess;

    public static VirtualStorage getInstance() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;
        ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
        getLogger().info("VirtualStorage has been enabled!");
        ConfigUtils.loadDefaultConfig();
        plugin.getServer().getPluginManager().registerEvents(new DVSEvent(), plugin);
        getCommand("창고").setExecutor(new DVSCommand());
    }

    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(HumanEntity::closeInventory);
        udata.keySet().forEach(DVSFunction::saveData);
        getLogger().info("VirtualStorage has been disabled!");
    }
}
