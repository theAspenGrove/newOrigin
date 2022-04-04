package net.mov51.neworigin.originCommand;

import net.mov51.periderm.luckPerms.AspenMetaKey;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.mov51.neworigin.NewOrigin.*;
import static net.mov51.periderm.paper.Locations.*;
import static net.mov51.neworigin.originCommand.NewOriginCommand.commands.*;

public class NewOriginCommand implements CommandExecutor {

    public static AspenMetaKey Origin = new AspenMetaKey("NewOrigin_","Origin");

    public enum commands {
        NewOrigin("Origin","NewOrigin"),

        here("Here","NewOrigin","here"),
        get("Get","NewOrigin", "get"),
        watch("Watch","NewOrigin", "watch"),
        stop(watch.Permission, "NewOrigin","stop");

        String Root;
        String Permission;
        String SubCommand;

        commands(String Permission, String Root, String SubCommand){
            this.Permission = "NewOrigin." + Permission;
            this.Root = Root;
            this.SubCommand = SubCommand;
        }

        commands(String Permission, String Root){
            this.Permission = "NewOrigin." + Permission;
            this.Root = Root;
        }
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        //is for me? 👉 👈
        if(!command.getName().equalsIgnoreCase(NewOrigin.Root)) return false;
        //is player??
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        //does player have permission?
        if(!p.hasPermission(NewOrigin.Permission)) return false;
        //has more than one arg??
        if(args.length >= 1){
            //then begin
            if(args[0].equalsIgnoreCase(here.SubCommand) && p.hasPermission(here.Permission)){
                //set command
                Location l = p.getLocation().toBlockLocation();
                String LString = LocationToString(l);
                LPHelper.setMetaValue(p,Origin,LString);
                playerLoop.Players.add(p.getUniqueId());
                chatHelper.sendChat(p,"Your Origin is now set to " + PrettyBlockLocation(l) + "!");
                return true;
            }else if(args[0].equalsIgnoreCase(get.SubCommand) && p.hasPermission(get.Permission)){
                //get command
                chatHelper.sendChat(p,"Your Origin is currently set to "+ PrettyBlockLocation(LocationFromString(LPHelper.getMetaValue(p,Origin))) + ".");
                return true;
            }else if(args[0].equalsIgnoreCase(watch.SubCommand) && p.hasPermission(watch.Permission)){
                //watch command
                if(!playerLoop.Players.contains(p.getUniqueId())){
                    playerLoop.Players.add(p.getUniqueId());
                    chatHelper.sendChat(p,"You are now watching your Origin!");
                }else{
                chatHelper.sendChat(p,"Sorry, you're already watching your Origin.");
                }
                return true;
            }else if(args[0].equalsIgnoreCase(stop.SubCommand) && p.hasPermission(stop.Permission)){
                //stop watching command
                if(playerLoop.Players.remove(p.getUniqueId())){
                    chatHelper.sendChat(p,"You are no longer watching your Origin.");
                }else{
                    chatHelper.sendChat(p,"You weren't watching your Origin!");
                }
                return true;
            }
        }
        return false;
    }
}
