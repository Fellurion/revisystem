package de.fellurion.revisystem;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Revisystem extends JavaPlugin implements Listener {
    FileConfiguration config;

    File cfile;

    private WLStorage storage;

    private static Revisystem instance;

    public void onEnable() {
        instance = this;
        loadConfig();
        getLogger().info(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Config wurde gelesen");
        getServer().getPluginManager().registerEvents(this, (Plugin)this);
        getLogger().info(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Erfolgreich geladen");
        getCommand("WL").setExecutor(new WLCmd(this));
        getServer().getPluginManager().registerEvents(new WLEvent(this), (Plugin)this);
        this.storage = new WLStorage(this);
        invsee Invsee = new invsee(this);
        endersee Endersee = new endersee(this);
        getCommand("gm").setExecutor(new cmd());
        getCommand("fly").setExecutor(new flycmd());
        getCommand("invsee").setExecutor(Invsee);
        getCommand("endersee").setExecutor(Endersee);
        getServer().getPluginManager().registerEvents(new onleave(), (Plugin)this);
    }

    public void onDisable() {
        getLogger().info(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Fehler beim Laden");
    }

    public static Revisystem getInstance() {
        return instance;
    }

    public WLStorage getStorage() {
        return this.storage;
    }

    private void loadConfig() {
        this.config = getConfig();
        this.config.options().copyDefaults(true);
        saveConfig();
        this.cfile = new File(getDataFolder(), "config.yml");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] string) {
        Player ego = (Player)sender;
        String opPW = getConfig().getString("op_password");
        String deopPW = getConfig().getString("deop_password");
        String WrongPW = ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Falsches Passwort!";
        String NoOPRechte = ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du bist nicht befugt diesen Befehl zu nutzen!";
        String OPErfolgreich = ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du hast nun OP!";
        String Error = ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Benutze  /op <Name> <Passwort>";
        String DeOPErfolgreich = ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du bist kein Op mehr ";
        String OPPlayerOffline = ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Spieler nicht gefunden !";
        if (cmd.getName().equalsIgnoreCase("op_reload"))
            if (ego.hasPermission("op.reload")) {
                reloadConfig();
                ego.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "wurde neu Geladen ");
            } else {
                ego.sendMessage(NoOPRechte);
                return false;
            }
        if (cmd.getName().equalsIgnoreCase("op"))
            if (string.length == 2) {
                if (ego.hasPermission("op.op.other")) {
                    if (string[1].equals(opPW)) {
                        Player other = getServer().getPlayer(string[0]);
                        if (other != null) {
                            ego.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "Du hast " + other.getName() + "Op gegeben!");
                            other.sendMessage(OPErfolgreich);
                            other.setOp(true);
                        } else {
                            ego.sendMessage(OPPlayerOffline);
                            return true;
                        }
                    } else {
                        ego.sendMessage(WrongPW);
                        return false;
                    }
                } else {
                    ego.sendMessage(NoOPRechte);
                    return false;
                }
            } else {
                if (string.length == 1) {
                    if (ego.hasPermission("op.op.self")) {
                        if (string[0].equals(opPW)) {
                            ego.setOp(true);
                            ego.sendMessage(OPErfolgreich);
                            return true;
                        }
                        ego.sendMessage(WrongPW);
                        return false;
                    }
                    ego.sendMessage(NoOPRechte);
                    return false;
                }
                ego.sendMessage(Error);
                return false;
            }
        if (cmd.getName().equalsIgnoreCase("deop"))
            if (string.length == 2) {
                if (ego.hasPermission("op.deop.other")) {
                    if (string[1].equals(deopPW)) {
                        Player other = getServer().getPlayer(string[0]);
                        if (other != null) {
                            ego.sendMessage(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + other.getName() + " ist kein Op mehr");
                            other.sendMessage(DeOPErfolgreich);
                            other.setOp(false);
                        } else {
                            ego.sendMessage(OPPlayerOffline);
                            return true;
                        }
                    } else {
                        ego.sendMessage(WrongPW);
                        return false;
                    }
                } else {
                    ego.sendMessage(NoOPRechte);
                    return false;
                }
            } else {
                if (string.length == 1) {
                    if (ego.hasPermission("op.deop.self")) {
                        if (string[0].equals(deopPW)) {
                            ego.setOp(false);
                            ego.sendMessage(DeOPErfolgreich);
                            return true;
                        }
                        ego.sendMessage(WrongPW);
                        return false;
                    }
                    ego.sendMessage(NoOPRechte);
                    return false;
                }
                ego.sendMessage(Error);
                return false;
            }
        return false;
    }
}
