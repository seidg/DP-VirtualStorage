package com.darksoldier1404.dvs.commands;

import com.darksoldier1404.dvs.VirtualStorage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DVSCommand implements CommandExecutor, TabCompleter {
    private final String prefix = VirtualStorage.getInstance().prefix;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage(prefix + "플레이어만 사용 가능한 명령어 입니다.");
            return false;
        }
        if(args.length == 0) {
            p.sendMessage(prefix + "/창고 선택 - 가상 창고 선택 GUI를 오픈합니다.");
            p.sendMessage(prefix + "/창고 구매 - 가상 창고를 추가로 구매합니다.");
            p.sendMessage(prefix + "/창고 ");
            return false;
        }
        if(args[0].equals("t")) {
            p.openInventory(Bukkit.createInventory(p, (Integer.parseInt(args[1])*9), "창고"));
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
