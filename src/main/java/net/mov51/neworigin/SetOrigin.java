package net.mov51.neworigin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.mov51.neworigin.LpHelper.*;
import static net.mov51.neworigin.LpHelper.MetaKey.Origin;
import static net.mov51.neworigin.LpHelper.PermissionNode.*;
import static net.mov51.neworigin.NewOrigin.playerLoop;

public class SetOrigin implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            //is for me? 👉 👈
            if(args.length >= 1){
                Player p = (Player) sender;
                if(args[0].equalsIgnoreCase("set") && p.hasPermission(set.node)){
                    Location l = p.getLocation().toBlockLocation();
                    String LString = LocationToString(l);

                    setMetaValue(p,Origin,LString);
                    return true;
                }else if(args[0].equalsIgnoreCase("get") && p.hasPermission(get.node)){
                    System.out.println(getMetaValue(p,Origin));

                    return true;
                }else if(args[0].equalsIgnoreCase("watch") && p.hasPermission(watch.node)){
                    if(!playerLoop.Players.contains(p.getUniqueId())){
                        playerLoop.Players.add(p.getUniqueId());
                    }
                    return true;
                }else if(args[0].equalsIgnoreCase("unwatch") && p.hasPermission(unWatch.node)){
                    playerLoop.Players.remove(p.getUniqueId());
                    return true;
                }
            }
        }
        return false;
    }
}
