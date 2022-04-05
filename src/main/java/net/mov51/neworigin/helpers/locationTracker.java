package net.mov51.neworigin.helpers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static net.mov51.neworigin.helpers.PlayerLoop.*;

public class locationTracker {
    public static HashMap<Player, Location> PlayerCompass = new HashMap<>();;
    public static void trackPlayers(Plugin plugin) {

        new BukkitRunnable() {
            public void run() {
                //loops through a list of players who have lode stone compasses in their hand.
                //keyed by player with the location the compass points to as a value
                for (Player p : PlayerCompass.keySet()) {
                    //get relative location.
                    Location RelativeLocation = CompareLocations(PlayerCompass.get(p), p.getLocation().toBlockLocation());

                    if (RelativeLocation != null) {
                        //send that player an actionbar with the relative location if it isn't null
                        //will only be null if they aren't in the same world
                        sendActionBar(p, Math.round(RelativeLocation.getX()) + " "
                                + Math.round(RelativeLocation.getY()) + " "
                                + Math.round(RelativeLocation.getZ()));
                    }
                }
            }
            //send actionbars more often than we check for compasses because everyone in the list has been confirmed to have a compass,
            // and we want frequent updates about the relative location
        }.runTaskTimer(plugin, 0L, 2);
    }
}
