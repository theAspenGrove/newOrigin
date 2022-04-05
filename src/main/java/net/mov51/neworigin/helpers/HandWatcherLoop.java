package net.mov51.neworigin.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.mov51.neworigin.helpers.locationTracker.PlayerCompass;
import static net.mov51.neworigin.helpers.locationTracker.trackPlayers;

public class HandWatcherLoop {

    public static void startWatching(Plugin plugin){
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    //main hand check
                    ItemStack compass;
                        if(p.getInventory().getItemInMainHand().getType() == Material.COMPASS){
                            //main hand check
                            //add compass to var for checking
                            compass = p.getInventory().getItemInMainHand();
                            if(((CompassMeta)compass.getItemMeta()).hasLodestone()){
                                //add the player and the location their compass is attached to if their holding a compass with a lode stone
                                PlayerCompass.put(p, ((CompassMeta)compass.getItemMeta()).getLodestone());
                            }
                        }else if(p.getInventory().getItemInOffHand().getType() == Material.COMPASS){
                            //off hand check
                            //add compass to var for checking
                                compass = p.getInventory().getItemInOffHand();
                                if(((CompassMeta)compass.getItemMeta()).hasLodestone()){
                                    //add the player and the location their compass is attached to if their holding a compass with a lode stone
                                    PlayerCompass.put(p, ((CompassMeta)compass.getItemMeta()).getLodestone());
                                }
                        }else{
                            //remove player from the list if they don't have a compass
                            PlayerCompass.remove(p);
                            }
                }
            }
        }.runTaskTimer(plugin, 0L, 10);
    }
}
