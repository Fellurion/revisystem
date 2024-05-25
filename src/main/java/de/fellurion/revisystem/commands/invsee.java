package de.fellurion.revisystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class invsee implements CommandExecutor 
{
    private final JavaPlugin plugin;

    public invsee(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
        if (!(send instanceof Player)) {
            send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Nur ein Spieler Kann diesen Befehl anwenden! ");
            return true;
        }
        Player sendPlayer = (Player)send;
        if (!sendPlayer.hasPermission("system.invsee.see") && !sendPlayer.hasPermission("system.invsee.edit")) {
            send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du hast keine Berechtigung diesen Befehl zu nutzen");
            return true;
        }
        if (args.length != 1) {
            send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Verwendung: /invsee <Spieler>");
            return true;
        }
        Player targetPlayer = sendPlayer.getServer().getPlayer(args[0]);
        if (targetPlayer == null) {
            send.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Spieler nicht gefunden.");
            return true;
        }
        if (sendPlayer.hasPermission("system.invsee.edit")) {
            PlayerInventory playerInventory = targetPlayer.getInventory();
            sendPlayer.openInventory((Inventory)playerInventory);
        } else if (sendPlayer.hasPermission("system.invsee.see")) {
            PlayerInventory playerInventory = targetPlayer.getInventory();
            Inventory readonly = sendPlayer.getServer().createInventory((InventoryHolder)null, playerInventory.getSize(), targetPlayer.getName());
            readonly.setContents(playerInventory.getContents());
            sendPlayer.openInventory(readonly);
        }
        return true;
    }
    
    
}
