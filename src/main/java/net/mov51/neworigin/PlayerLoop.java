package net.mov51.neworigin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.UUID;

import static net.mov51.neworigin.LpHelper.LocationFromString;
import static net.mov51.neworigin.LpHelper.MetaKey.Origin;
import static net.mov51.neworigin.LpHelper.getMetaValue;

public class PlayerLoop {
    protected NewOrigin plugin;
    public ArrayList<UUID> Players = new ArrayList<>();
    private BukkitTask projectorTask;

    public PlayerLoop(NewOrigin plugin) {
        this.plugin = plugin;
        start();
    }

    public void start() {
        if (projectorTask != null) {
            projectorTask.cancel();
            projectorTask = null;
        }
        int updateRate = 1;
        projectorTask = new BukkitRunnable() {
            @Override
            public void run() {
                if(!Players.isEmpty()){
                    for (UUID u : Players) {
                        Player p = Bukkit.getPlayer(u);
                        if(p != null){
                            Location RelativeLocation = CompareLocations(getOrigin(p), p.getLocation().toBlockLocation());
                            if(RelativeLocation != null){
                                sendActionBar(p, Math.round(RelativeLocation.getX()) + " "
                                        + Math.round(RelativeLocation.getY()) + " "
                                        + Math.round(RelativeLocation.getZ()));
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, updateRate, 5);
    }

    public static void sendActionBar(Player p, String s){
        TextComponent textComponent = Component.text(s).color(NamedTextColor.GOLD);
        p.sendActionBar(textComponent);
    }

    public static Location getOrigin(Player p){
        return LocationFromString(getMetaValue(p,Origin));
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
