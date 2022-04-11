package net.mov51.neworigin.helpers;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class locationTracker {
    public static HashMap<Player, HeldCompass> PlayerCompass = new HashMap<>();;
    public static void trackPlayers(Plugin plugin) {

        new BukkitRunnable() {
            public void run() {
                //loops through a list of players who have lode stone compasses in their hand.
                //keyed by player with the location the compass points to as a value
                for (Player p : PlayerCompass.keySet()) {
                    //get relative location.
                    //we already know that the compassMeta has a lodestone attached
                    Location RelativeLocation =
                            CompareLocations(PlayerCompass.get(p).getTarget(), p.getLocation().toBlockLocation());
//                    int divisor = 0;
                    if (RelativeLocation != null) {
                        //send that player an actionbar with the relative location if it isn't null
                        //will only be null if they aren't in the same world
//                        if(PlayerCompass.get(p).hasDisplayName()){
//                            Pattern pattern = Pattern.compile("\\((\\d*?)\\)");
//                            //confirmed compass has a display name
//                            Matcher matcher = pattern.matcher(Objects.requireNonNull(PlayerCompass.get(p).displayName()).toString());
//                            if (matcher.find())
//                            {
//                                divisor = Integer.parseInt(matcher.group(1));
//                            }
//                        }



                        p.sendActionBar(LegacyComponentSerializer.legacyAmpersand().deserialize(
                                colorCords(RelativeLocation.getBlockX(),PlayerCompass.get(p).getTargetRadii()) + colorCords(RelativeLocation.getBlockY(),PlayerCompass.get(p).getTargetRadii()) + colorCords(RelativeLocation.getBlockZ(),PlayerCompass.get(p).getTargetRadii())));
                    }
                }
            }
            //send actionbars more often than we check for compasses because everyone in the list has been confirmed to have a compass,
            // and we want frequent updates about the relative location
        }.runTaskTimer(plugin, 0L, 2);
    }

    public static Location CompareLocations(Location L2,Location L1){
        if(L1.getWorld().equals(L2.getWorld())){

            double RLX = L1.getX() - L2.getX();
            double RLY = L1.getY() - L2.getY();
            double RLZ = L1.getZ() - L2.getZ();

            return new Location(L1.getWorld(),RLX,RLY,RLZ);
        }
        return null;
    }

    public static String colorCords(int cord, int compass){
        return (Math.abs(cord) == compass ? "&2" + cord : "&6" + cord) + "&6 ";
    }

}
