package com.maddyjace.lobbytools;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbyTools extends JavaPlugin {

    @Override
    public void onEnable() {
        Get.initialize(this);
        InventoryCheck.start();
        Bukkit.getConsoleSender().sendMessage("§6Lobby§bTools§f:");
        Bukkit.getConsoleSender().sendMessage("\t§a拥有 §cLobbyTools.admin §a权限将跳过检查！");
        Bukkit.getConsoleSender().sendMessage("\t§a开发作者: 2743063754");
        Bukkit.getConsoleSender().sendMessage("\t§a问题反馈: 605567487");
    }

    @Override
    public void onDisable() {
        InventoryCheck.stop();
    }

}
