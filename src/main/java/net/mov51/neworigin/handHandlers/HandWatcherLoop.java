package net.mov51.neworigin.handHandlers;

import net.mov51.neworigin.helpers.HeldCompass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.mov51.neworigin.helpers.locationTracker.PlayerCompass;

public class HandWatcherLoop {

    public static void startWatching(Plugin plugin){
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    //main hand check
                    ItemStack compass;
                    //todo check if player exists in map
                    //todo if they do check if holding compass
                    // if not, remove them
                    // if so, check the name of the compass and if it matches the stored one
                    // if they don't, check if holding compass
                    // if they are, update the compassMap entry
                        if(p.getInventory().getItemInMainHand().getType() == Material.COMPASS){
                            //main hand check
                            //add compass to var for checking
                            compass = p.getInventory().getItemInMainHand();
                            if(((CompassMeta)compass.getItemMeta()).hasLodestone()){
                                //add the player and the location their compass is attached to if their holding a compass with a lode stone
                                PlayerCompass.put(p, new HeldCompass(compass));
                            }
                        }else if(p.getInventory().getItemInOffHand().getType() == Material.COMPASS){
                            //off hand check
                            //add compass to var for checking
                                compass = p.getInventory().getItemInOffHand();
                                if(((CompassMeta)compass.getItemMeta()).hasLodestone()){
                                    //add the player and the location their compass is attached to if their holding a compass with a lode stone
                                    PlayerCompass.put(p, new HeldCompass(compass));
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
