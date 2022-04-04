package net.mov51.neworigin.originCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static net.mov51.neworigin.originCommand.NewOriginCommand.commands.*;

public class NewOriginTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String s, String[] strings) {
        if(command.getName().equals(NewOrigin.Root)){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                return whatCanRun(p);
            }
        }
        return null;
    }

    public static List<String> whatCanRun(Player p){
        ArrayList<String> l = new ArrayList<>();
        if(p.hasPermission(here.Permission)) l.add(here.SubCommand);
        if(p.hasPermission(get.Permission)) l.add(get.SubCommand);
        if(p.hasPermission(watch.Permission)) l.add(watch.SubCommand);
        if(p.hasPermission(stop.Permission)) l.add(stop.SubCommand);
        return l;
    }
}
