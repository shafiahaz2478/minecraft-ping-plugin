package me.shafi.ping_plugin.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.*;

import org.bukkit.event.Listener;


import java.lang.reflect.Field;

import java.lang.reflect.Method;


import static org.bukkit.Bukkit.getServer;

public class ping implements CommandExecutor, Listener {


    Class<?> CPClass;

    String serverName  = getServer().getClass().getPackage().getName(),
            serverVersion = serverName.substring(serverName.lastIndexOf(".") + 1, serverName.length());

    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {



        String prefix = ChatColor.GOLD + "[PING]" + ChatColor.RESET;

        if (cmd.getName().equalsIgnoreCase("ping"))
        {

            if(args.length == 0) {

                if (!(sender instanceof Player)){
                    sender.sendMessage(prefix + " Your ping: " + ChatColor.DARK_RED + "0 ms" + ChatColor.RESET + " you silly console!");
                    return true;
                } else {


                        final Player p = (Player) sender;
                        sender.sendMessage(prefix + " Your ping: " + ChatColor.DARK_RED + getPing(p) + " ms" + ChatColor.RESET);
                        return true;

                }
            } else if(args.length == 1) {

                    Player argplayer = getServer().getPlayer(args[0]);
                    if(argplayer == null) {
                        sender.sendMessage(prefix + " The player " + args[0] +" could not be found");
                        return true;
                    }

                    sender.sendMessage(prefix + " " + getServer().getPlayer(args[0]).getDisplayName().toString() + "'s ping: " + ChatColor.DARK_RED + getPing(argplayer) + " ms" + ChatColor.RESET);
                    return true;



            } else {

                sender.sendMessage(prefix + ChatColor.YELLOW +" Plugin help:");
                sender.sendMessage(prefix + " /ping" + ChatColor.GRAY + " - " + ChatColor.DARK_RED + "Check your ping.");
                sender.sendMessage(prefix + " /ping <player>" + ChatColor.GRAY + " - " + ChatColor.DARK_RED + "Check ping of player.");
                return true;
            }

        }
        return false;
    }

    public int getPing(Player p) {
        try {
            CPClass = Class.forName("org.bukkit.craftbukkit." + serverVersion + ".entity.CraftPlayer");
            Object CraftPlayer = CPClass.cast(p);

            Method getHandle = CraftPlayer.getClass().getMethod("getHandle", new Class[0]);
            Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);

            Field ping = EntityPlayer.getClass().getDeclaredField("ping");

            return ping.getInt(EntityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }




}
