package net.mov51.neworigin;

import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.matcher.NodeMatcher;
import net.luckperms.api.node.types.MetaNode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

import static net.mov51.neworigin.NewOrigin.LPapi;

public class LpHelper {
    public enum MetaKey {
        topLevelMetaKey("NewOrigin_"),

        Origin(topLevelMetaKey.key + "Origin");

        final String key;

        MetaKey(String key){
            this.key = key;
        }
    }

    public static String getMetaValue(Player p, MetaKey metaKey){
        // obtain CachedMetaData - the easiest way is via the PlayerAdapter
        // of course, you can get it via a User too if the player is offline.
        CachedMetaData metaData = LPapi.getPlayerAdapter(Player.class).getMetaData(p);

        // query & parse the meta value
        return metaData.getMetaValue(metaKey.key);
    }

    public static void setMetaValue(Player p, MetaKey metaKey, String value){
        // obtain a User instance (by any means! see above for other ways)
        User user = LPapi.getPlayerAdapter(Player.class).getUser(p);

        // create a new MetaNode holding the level value
        // of course, this can have context/expiry/etc. too!
        MetaNode node = MetaNode.builder(metaKey.key, value).build();

        // clear any existing meta nodes with the same key - we want to override
        user.data().clear(NodeMatcher.metaKey(metaKey.key));
        // add the new node
        user.data().add(node);

        // save!
        LPapi.getUserManager().saveUser(user);
    }

    public static String LocationToString(Location l){
        String world = Objects.requireNonNull(l.getWorld()).getName();
        String x = String.valueOf(l.getX());
        String y = String.valueOf(l.getY());
        String z = String.valueOf(l.getZ());
        String Y = String.valueOf(l.getYaw());
        String P = String.valueOf(l.getPitch());

        return  world  + "," + x + "," + y + "," + z  + "," + Y  + "," + P;
    }

    public static Location LocationFromString(String s) {
        //split the provided string
        String [] sA = s.split(",");

        //separate the array and typecast
        World world = Bukkit.getWorld(sA[0]);
        double x = Double.parseDouble(sA[1]);
        double y = Double.parseDouble(sA[2]);
        double z = Double.parseDouble(sA[3]);
        float Y = Float.parseFloat(sA[4]);
        float P = Float.parseFloat(sA[5]);

        //Parse then return the location
        return new Location(world, x, y, z, Y, P);
    }

}
