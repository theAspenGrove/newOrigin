package net.mov51.neworigin;

import net.luckperms.api.LuckPerms;
import net.mov51.neworigin.helpers.PlayerLoop;
import net.mov51.neworigin.originCommand.NewOriginCommand;
import net.mov51.neworigin.originCommand.NewOriginTabComplete;
import net.mov51.periderm.luckPerms.AspenLuckPermsHelper;
import net.mov51.periderm.paper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class NewOrigin extends JavaPlugin {

    public static AspenLuckPermsHelper LPHelper;
    public static Logger logger;
    public static PlayerLoop playerLoop;
    public static ChatHelper chatHelper;

    public static org.bukkit.plugin.Plugin plugin = null;

    @Override
    public void onEnable() {
        plugin=this;
        //get Logger
        logger = NewOrigin.plugin.getLogger();

        LPHelper = new AspenLuckPermsHelper(logger);

        playerLoop = new PlayerLoop(this);

        Objects.requireNonNull(this.getCommand("NewOrigin")).setExecutor(new NewOriginCommand());
        Objects.requireNonNull(getCommand("NewOrigin")).setTabCompleter(new NewOriginTabComplete());

        chatHelper = new ChatHelper("New Origin");

        logger.info("Your Origins can now be REDEFINED!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
