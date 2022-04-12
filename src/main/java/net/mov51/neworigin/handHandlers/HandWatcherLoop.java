package net.mov51.neworigin.handHandlers;

import net.mov51.neworigin.helpers.HeldCompass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.mov51.neworigin.handHandlers.ItemHeldWatcher.updatePlayerCompass;

public class HandWatcherLoop {

    public static void startWatching(Plugin plugin){
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(p.getInventory().getItemInMainHand().getType() == Material.COMPASS){
                        updatePlayerCompass(p,p.getInventory().getItemInMainHand());
                    }else if(p.getInventory().getItemInOffHand().getType() == Material.COMPASS){
                        updatePlayerCompass(p,p.getInventory().getItemInOffHand());
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 40);
    }
}
