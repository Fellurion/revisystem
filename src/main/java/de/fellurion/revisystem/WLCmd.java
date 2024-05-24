package de.fellurion.revisystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WLCmd implements CommandExecutor 
{

    private Revisystem revisystem;

    String prefix;

    public WLCmd(Revisystem revisystem) {
        this.prefix = ChatColor.DARK_RED + "[System]" + ChatColor.GRAY;
        this.revisystem = revisystem;
    }

    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if (!arg0.hasPermission("netlist.admin"))
            return true;
        remanage(arg0, arg3);
        return true;
    }

    private void remanage(CommandSender snd, String[] args) {
        if (args.length == 0) {
            Utility.sendMsg(snd, "&aSystem-Whitelist &7>");
            Utility.sendMsg(snd, "&e> &7/wl add &f<name>");
            Utility.sendMsg(snd, "&e> &7/wl remove &f<name>");
            Utility.sendMsg(snd, "&e> &7/wl list");
            Utility.sendMsg(snd, "&e> &7/wl on");
            Utility.sendMsg(snd, "&e> &7/wl off");
            Utility.sendMsg(snd, "&e> &7/wl reload");
            return;
        }
        String str;
        switch ((str = args[0].toLowerCase()).hashCode()) {
            case -934641255:
                if (!str.equals("reload"));
                break;
            case -934610812:
                if (str.equals("remove")) {
                    if (args.length < 2) {
                        Utility.sendMsg(snd, ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "gib einen namen ein!");
                        return;
                    }
                    String name = args[1];
                    if (args.length > 2)
                        name = String.valueOf(name) + " " + args[2];
                    if (args.length > 3)
                        name = String.valueOf(name) + " " + args[2] + " " + args[3];
                    this.revisystem.getStorage().removeWhitelist(name);
                    Utility.sendMsg(snd, String.valueOf(String.valueOf(this.prefix)) + "Whitelist entfernt: &c" + name);
                    return;
                }
            case 3551:
                if (str.equals("on")) {
                    this.revisystem.getStorage().setWhitelist(Boolean.valueOf(true));
                    Utility.sendMsg(snd, String.valueOf(String.valueOf(this.prefix)) + "&fWhitelist ist jetzt Aktiv!");
                    return;
                }
            case 96417:
                if (str.equals("add")) {
                    if (args.length < 2) {
                        Utility.sendMsg(snd, ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "gib einen namen ein den du hinzufwillst !");
                        return;
                    }
                    String name = args[1];
                    if (args.length > 2)
                        name = String.valueOf(name) + " " + args[2];
                    if (args.length > 3)
                        name = String.valueOf(name) + " " + args[2] + " " + args[3];
                    this.revisystem.getStorage().addWhitelist(name);
                    Utility.sendMsg(snd, String.valueOf(String.valueOf(this.prefix)) + "Whitelisted '&a" + name + "&7'");
                    return;
                }
            case 109935:
                if (str.equals("off")) {
                    this.revisystem.getStorage().setWhitelist(Boolean.valueOf(false));
                    Utility.sendMsg(snd, String.valueOf(String.valueOf(this.prefix)) + "Whitelist wurde deaktiviert");
                    return;
                }
            case 3322014:
                if (str.equals("list")) {
                    String names = "";
                    for (String str1 : this.revisystem.getStorage().getWhiteLists())
                        names = String.valueOf(String.valueOf(names)) + str1 + "&e&l, &7";
                    Utility.sendMsg(snd, "&a&lWhitelisted: &7" + names);
                    return;
                }
            default:
                Utility.sendMsg(snd, "&aSystem_Whitelist &7>");
                Utility.sendMsg(snd, "&e> &7/wl add &f<name>");
                Utility.sendMsg(snd, "&e> &7/wl remove &f<name>");
                Utility.sendMsg(snd, "&e> &7/wl list");
                Utility.sendMsg(snd, "&e> &7/wl on");
                Utility.sendMsg(snd, "&e> &7/wl off");
                Utility.sendMsg(snd, "&e> &7/wl reload");
                return;
        }
        this.revisystem.getStorage().reload();
    }
    
    
    
}
