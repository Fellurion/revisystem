package de.fellurion.revisystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class endersee implements CommandExecutor
{
    private final JavaPlugin plugin;

    public endersee(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du musst ein Spieler sein, um diesen Befehl zu verwenden.");
            return true;
        }
        Player senderPlayer = (Player)sender;
        if (!senderPlayer.hasPermission("system.enderchest.see") && !senderPlayer.hasPermission("system.enderchest.edit")) {
            sender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du hast nicht die erforderlichen Berechtigungen fdiesen Befehl.");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Verwendung: /endersee <Spieler>");
            return true;
        }
        Player targetPlayer = senderPlayer.getServer().getPlayer(args[0]);
        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Spieler nicht gefunden.");
            return true;
        }
        if (senderPlayer.hasPermission("system.enderchest.edit")) {
            Inventory targetEnderchest = targetPlayer.getEnderChest();
            senderPlayer.openInventory(targetEnderchest);
        } else if (senderPlayer.hasPermission("system.enderchest.see")) {
            Inventory targetEnderchest = targetPlayer.getEnderChest();
            Inventory readOnlyEnderchest = this.plugin.getServer().createInventory(null, targetEnderchest.getSize(), "Endertruhe von " + targetPlayer.getName());
            readOnlyEnderchest.setContents(targetEnderchest.getContents());
            senderPlayer.openInventory(readOnlyEnderchest);
        }
        return true;
    }
    
    
    
    
}
