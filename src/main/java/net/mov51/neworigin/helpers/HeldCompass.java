package net.mov51.neworigin.helpers;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeldCompass {

    String DisplayName = "";
    CompassMeta CompassMeta;
    Location Target;
    boolean hasTargetRadii = false;
    int TargetRadii = 0;

    public HeldCompass(ItemStack compass){
        this.CompassMeta = (CompassMeta) compass.getItemMeta();
        this.Target = this.CompassMeta.getLodestone();

        if(this.CompassMeta.hasDisplayName()){
            Pattern pattern = Pattern.compile("\\((\\d*?)\\)");
            //confirmed compass has a display name
            this.DisplayName = String.valueOf(this.CompassMeta.displayName());
            Matcher matcher = pattern.matcher((this.DisplayName));
            if (matcher.find())
            {
                this.hasTargetRadii = true;
                this.TargetRadii = Integer.parseInt(matcher.group(1));
            }
        }
    }

    public int getTargetRadii() {
        return TargetRadii;
    }

    public boolean hasTargetRadii() {
        return hasTargetRadii;
    }

    public Location getTarget() {
        return Target;
    }

    public ItemMeta getMeta() {
        return CompassMeta;
    }

    public String getDisplayName() {
        return DisplayName;
    }
}
