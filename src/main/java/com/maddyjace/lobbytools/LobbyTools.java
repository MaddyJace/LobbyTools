package com.maddyjace.lobbytools;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbyTools extends JavaPlugin {

    @Override
    public void onEnable() {
        Get.initialize(this);
        InventoryCheck.start();
        Bukkit.getConsoleSender().sendMessage("§6Lobby§bTools§f: §a拥有 §cLobbyTools.admin §a权限将跳过检查！");
    }

    @Override
    public void onDisable() {
        InventoryCheck.stop();
    }

}
