package net.mov51.neworigin;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class NewOrigin extends JavaPlugin {

    public static LuckPerms LPapi;
    public static Logger logger;
    public static PlayerLoop playerLoop;

    public static org.bukkit.plugin.Plugin plugin = null;

    @Override
    public void onEnable() {
        plugin=this;
        //get Logger
        logger = NewOrigin.plugin.getLogger();

        playerLoop = new PlayerLoop(this);

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LPapi = provider.getProvider();
            logger.info("LuckPerms dependency loaded!");
        }

        Objects.requireNonNull(this.getCommand("NewOrigin")).setExecutor(new SetOrigin());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
