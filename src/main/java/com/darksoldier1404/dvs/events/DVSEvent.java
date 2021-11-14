package com.darksoldier1404.dvs.events;

import com.darksoldier1404.dvs.VirtualStorage;
import com.darksoldier1404.dvs.functions.DVSFunction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DVSEvent implements Listener {
    private final VirtualStorage plugin = VirtualStorage.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        DVSFunction.initData(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        DVSFunction.quitAndSaveData(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getView().getTitle().contains("창고 선택")) e.setCancelled(true);

    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {

    }
}
