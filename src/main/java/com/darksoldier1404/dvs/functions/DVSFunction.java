package com.darksoldier1404.dvs.functions;

import com.darksoldier1404.dvs.VirtualStorage;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class DVSFunction {
    private static final VirtualStorage plugin = VirtualStorage.getInstance();


    public static void openStorageSelector(Player p) {
        UUID uuid = p.getUniqueId();
        Inventory inv = plugin.getServer().createInventory(null, 54, "§1창고 선택");
        plugin.udata.get(uuid).getConfigurationSection("Storage").getKeys(false).forEach(key -> inv.setItem(Integer.parseInt(key) - 1, plugin.udata.get(uuid).getItemStack("Storage." + key)));
        p.openInventory(inv);
    }

    public static void openStorage(Player p, int num, ItemStack bundle) {
        Inventory inv = plugin.getServer().createInventory(null, 54, "§1" + num + "번 창고");
        BundleMeta bm = (BundleMeta) bundle.getItemMeta();
        bm.getItems().forEach(inv::addItem);
        p.openInventory(inv);
    }

    public static void saveStorage(Player p, int num, Inventory inv) {
        UUID uuid = p.getUniqueId();
        YamlConfiguration data = plugin.udata.get(uuid);
        ItemStack bundle = new ItemStack(Material.BUNDLE);
        BundleMeta bm = (BundleMeta) bundle.getItemMeta();
        bm.setItems(Arrays.stream(inv.getContents()).toList());
        bundle.setItemMeta(bm);
        data.set("Storage." + num, bundle);
        saveData(uuid);
    }


    public static void initData(UUID uuid) {
        final File file = new File(plugin.getDataFolder(), "data/" + uuid + ".yml");
        if (!file.exists()) {
            YamlConfiguration data = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder() + "/data", uuid + ".yml"));
            data.set("Storage.1", new ItemStack(Material.BUNDLE));
            data.set("Player.MaxStorage", 1);
            try {
                data.save(new File(plugin.getDataFolder() + "/data", uuid + ".yml"));
                plugin.udata.put(uuid, data);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            YamlConfiguration data = YamlConfiguration
                    .loadConfiguration(new File(plugin.getDataFolder() + "/data", uuid + ".yml"));
            plugin.udata.put(uuid, data);
        }
    }

    public static void saveData(UUID uuid) {
        YamlConfiguration data = plugin.udata.get(uuid);
        try {
            data.save(new File(plugin.getDataFolder() + "/data", uuid + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void quitAndSaveData(UUID uuid) {
        saveData(uuid);
        plugin.udata.remove(uuid);
    }
}
