package net.mov51.neworigin.handHandlers;

import net.mov51.neworigin.helpers.HeldCompass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import static net.mov51.neworigin.helpers.locationTracker.PlayerCompass;

public class ItemHeldWatcher implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void PlayerItemHeldEvent(PlayerItemHeldEvent e) {
        updatePlayerCompass(e.getPlayer(),getStackBySlot(e.getPlayer(),e.getNewSlot()));
    }


    public static boolean updatePlayerCompass(Player p,ItemStack item){
        if(isLodeStoneCompass(item)){
            if(PlayerCompass.containsKey(p)){
                if(!doesDisplayNameMatch(p,item)){
                    PlayerCompass.put(p,new HeldCompass(item));
                    return true;
                }

            }else{
                PlayerCompass.put(p,new HeldCompass(item));
                return true;
            }
        }else{
            PlayerCompass.remove(p);
        }
        return false;
    }


    public static boolean isLodeStoneCompass(ItemStack stack){
        return stack != null && stack.getType() == Material.COMPASS && ((CompassMeta) stack.getItemMeta()).hasLodestone();
    }

    public static ItemStack getStackBySlot(Player p,int slot){
        return p.getInventory().getItem(slot);
    }

    public static boolean doesDisplayNameMatch(Player p, ItemStack i){
        return (i.getItemMeta().hasDisplayName() ? String.valueOf(i.getItemMeta().displayName()) : "").equals(PlayerCompass.get(p).getDisplayName());
    }

}
