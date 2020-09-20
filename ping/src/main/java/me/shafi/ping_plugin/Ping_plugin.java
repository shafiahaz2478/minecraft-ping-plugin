package me.shafi.ping_plugin;

import me.shafi.ping_plugin.commands.ping;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ping_plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(ChatColor.DARK_GREEN + "plugin ping is started");
        getCommand("ping").setExecutor(new ping());

    }


}
