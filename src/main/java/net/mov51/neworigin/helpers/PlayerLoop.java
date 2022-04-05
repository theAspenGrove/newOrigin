package net.mov51.neworigin.helpers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerLoop {

    public static void sendActionBar(Player p, String s){
        TextComponent textComponent = Component.text(s).color(NamedTextColor.GOLD);
        p.sendActionBar(textComponent);
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





}
