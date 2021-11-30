package com.darksoldier1404.dvs.events;

import com.darksoldier1404.dvs.VirtualStorage;
import com.darksoldier1404.dvs.functions.DVSFunction;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DVSEvent implements Listener {
    private final VirtualStorage plugin = VirtualStorage.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        DVSFunction.initData(p.getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        DVSFunction.quitAndSaveData(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getView().getTitle().contains("창고 선택")){
            e.setCancelled(true);
            if(e.getClickedInventory() == null) return;
            if(e.getClickedInventory().getType() == InventoryType.PLAYER) return;
            if(e.getCurrentItem() != null){
                if(e.getCurrentItem().getType() == Material.BUNDLE) {
                    DVSFunction.openStorage((Player) e.getWhoClicked(), e.getSlot()+1, e.getCurrentItem());
                    plugin.currentInventory.put(e.getWhoClicked().getUniqueId(), e.getSlot()+1);
                }
            }
        }
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        if(e.getView().getTitle().contains("번 창고")) {
            int num = plugin.currentInventory.get(e.getPlayer().getUniqueId());
            DVSFunction.saveStorage((Player) e.getPlayer(), num, e.getInventory());
            plugin.currentInventory.remove(e.getPlayer().getUniqueId());
        }
    }
}
