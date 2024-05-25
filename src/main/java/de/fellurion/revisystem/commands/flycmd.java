package de.fellurion.revisystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flycmd implements CommandExecutor
{
    public boolean onCommand(CommandSender send, Command cmd, String label, String[] string) {
        if (!cmd.getName().equalsIgnoreCase("fly") || send instanceof Player);
        if (!(send instanceof Player)) {
            send.sendMessage("Diesen Command kannst du nur als Spieler Ausf");
            return false;
        }
        Player player = (Player)send;
        if (player.hasPermission("whiteopsys.fly")) {
            if (string.length == 1 && player.hasPermission("whiteopsys.fly.speed")) {
                if (string[0].equalsIgnoreCase("1")) {
                    player.setFlySpeed(0.3F);
                    send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "du kannst nun Fliegen  mit flyspeed x2");
                } else if (string[0].equalsIgnoreCase("0")) {
                    player.setFlySpeed(0.1F);
                    send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "du fliegst nun wieder auf normaler Geschindikeit ");
                } else if (string[0].equalsIgnoreCase("2")) {
                    player.setFlySpeed(0.6F);
                    send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "du kannst nun Fliegen  mit flyspeed x3 ");
                } else if (string[0].equalsIgnoreCase("3")) {
                    player.setFlySpeed(0.9F);
                    send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "du kannst nun Fliegen  mit flyspeed x4");
                }
            } else {
                player.setAllowFlight(!player.getAllowFlight());
                player.sendMessage("Flugmodus: " + (player.getAllowFlight() ? "aktiviert" : "deaktiviert"));
            }
        } else {
            send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du hast leider keine Berechtigung zum Fliegen");
        }
        return true;
    }
    
    
    
}

