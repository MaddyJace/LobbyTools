package com.maddyjace.lobbytools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import java.util.Arrays;

public class InventoryCheck {

    // 1.12.2 的钟是 WATCH
    private static final Material TARGET_ITEM = Material.WATCH;
    private static BukkitTask task;

    /** 启动任务 */
    public static void start() {
        task = Bukkit.getScheduler().runTaskTimer(Get.plugin(), () -> {
            checkAllPlayers();
        }, 20L, 20L);
    }

    /** 取消任务 */
    public static void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    /** 检查所有在线玩家  */
    private static void checkAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            if (player.hasPermission("lobbytools.admin")) {
                continue;
            }

            if (!hotbarContains(player)) {
                resetHotbar(player);
                giveTargetItem(player);
            }
        }
    }

    /** 清空快捷栏 */
    private static void resetHotbar(Player player) {
        clearTargetItem(player);
        for (int i = 0; i <= 8; i++) { // 快捷栏索引 0~8
            player.getInventory().setItem(i, null); // 清空该格子
        }
    }

    /** 清空背包中的 TARGET_ITEM 物品  */
    private static void clearTargetItem(Player player) {
        ItemStack[] contents = player.getInventory().getContents();
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null && item.getType() == TARGET_ITEM) {
                player.getInventory().setItem(i, null); // 清空指定物品
            }
        }
    }

    /** 判断玩家快捷栏（0~8）是否含TARGET_ITEM物品 */
    private static boolean hotbarContains(Player player) {
        ItemStack[] contents = player.getInventory().getContents();
        // 0~8 是快捷栏
        for (int i = 0; i <= 8; i++) {
            ItemStack item = contents[i];
            if (item != null && item.getType() == TARGET_ITEM) {
                return true;
            }
        }
        return false;
    }

    /** 给予玩家一个物品，在快捷栏第四格 */
    private static void giveTargetItem(Player player) {
        ItemStack item = new ItemStack(TARGET_ITEM);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e游戏菜单");
        meta.setLore(Arrays.asList("", "§7手持该物品点击打开游戏菜单"));
        item.setItemMeta(meta);

        // NBT API
        // NBT.modify(item, nbt -> { nbt.setString("Key", "Value"); });

        player.getInventory().setItem(4, item);
    }

}
