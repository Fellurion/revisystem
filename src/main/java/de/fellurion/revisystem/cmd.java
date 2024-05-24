package de.fellurion.revisystem;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmd implements CommandExecutor
{

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] string) {
        Player player = (Player)commandSender;
        if (!command.getName().equalsIgnoreCase("gm") || commandSender instanceof Player);
        if (string.length == 1 && player.hasPermission("whiteopsys.gm")) {
            if (string[0].equalsIgnoreCase("1")) {
                player.setGameMode(GameMode.CREATIVE);
                commandSender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du bist nun im Spielmodus Creative!");
            } else if (string[0].equalsIgnoreCase("0")) {
                player.setGameMode(GameMode.SURVIVAL);
                commandSender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du bist nun im Spielmodus ");
            } else if (string[0].equalsIgnoreCase("3")) {
                player.setGameMode(GameMode.SPECTATOR);
                commandSender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du bist nun im Spielmodus Spectator!");
            } else if (string[0].equalsIgnoreCase("2")) {
                player.setGameMode(GameMode.ADVENTURE);
                commandSender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du bist nun im Spielmodus Abenteuer.");
            } else {
                commandSender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Um einen Spielmodus zu wechseln weinen Modus ");
                commandSender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "||1=Creative||0=Survival||3=Spectator||2=Adventure||");
            }
        } else {
            commandSender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du hast leider keine Berechtigung ");
        }
        return false;
    }
    
    
    
    
    
    
}
