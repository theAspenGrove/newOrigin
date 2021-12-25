package net.mov51.neworigin.originCommand;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.mov51.neworigin.helpers.LpHelper.*;
import static net.mov51.neworigin.helpers.LpHelper.MetaKey.Origin;
import static net.mov51.neworigin.NewOrigin.playerLoop;
import static net.mov51.neworigin.originCommand.NewOriginCommand.commands.*;

public class NewOriginCommand implements CommandExecutor {

    public enum commands {
        NewOrigin("Origin","NewOrigin"),

        set("Set","NewOrigin","set"),
        get("Get","NewOrigin", "get"),
        watch("Watch","NewOrigin", "watch"),
        unWatch("UnWatch","NewOrigin","unWatch");

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
            if(args[0].equalsIgnoreCase(set.SubCommand) && p.hasPermission(set.Permission)){
                //set command
                Location l = p.getLocation().toBlockLocation();
                String LString = LocationToString(l);
                //todo tell player they set their origin
                setMetaValue(p,Origin,LString);
                return true;
            }else if(args[0].equalsIgnoreCase(get.SubCommand) && p.hasPermission(get.Permission)){
                //get command
                //todo give player their origin location
                System.out.println(getMetaValue(p,Origin));
                return true;
            }else if(args[0].equalsIgnoreCase(watch.SubCommand) && p.hasPermission(watch.Permission)){
                //watch command
                if(!playerLoop.Players.contains(p.getUniqueId())){
                    //todo tell players they are now watching
                    playerLoop.Players.add(p.getUniqueId());
                }//else{
                // todo tell players they were already watching
                //}
                return true;
            }else if(args[0].equalsIgnoreCase(unWatch.SubCommand) && p.hasPermission(unWatch.Permission)){
                //stop watching command
                //todo tell players they are no longer watching
                playerLoop.Players.remove(p.getUniqueId());
                return true;
            }
        }
        return false;
    }
}
