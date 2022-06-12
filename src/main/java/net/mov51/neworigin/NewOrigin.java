package net.mov51.neworigin;

import net.mov51.neworigin.handHandlers.ItemHeldWatcher;
import net.mov51.periderm.chat.AspenChatHelper;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static net.mov51.neworigin.handHandlers.HandWatcherLoop.startWatching;
import static net.mov51.neworigin.helpers.locationTracker.trackPlayers;

public final class NewOrigin extends JavaPlugin {

    public static Logger logger;
    public static AspenChatHelper chatHelper;

    public static org.bukkit.plugin.Plugin plugin = null;

    @Override
    public void onEnable() {
        plugin=this;
        //get Logger
        logger = NewOrigin.plugin.getLogger();

        chatHelper = new AspenChatHelper("&6&l[&2New Origin&6&l]&r");

        trackPlayers(this);
        startWatching(this);
        getServer().getPluginManager().registerEvents(new ItemHeldWatcher(), this);

        logger.info("Your Origins can now be REDEFINED!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
