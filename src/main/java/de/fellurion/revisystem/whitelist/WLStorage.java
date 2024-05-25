package de.fellurion.revisystem;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;


public class WLStorage 
{
    private Revisystem revisystem;

    private ArrayList<String> whitelists = new ArrayList<>();

    private boolean WhitelistEnabled = true;

    private String nowhitelistmsg = "";

    public WLStorage(Revisystem revisystem) {
        this.revisystem = revisystem;
        reload();
    }

    public void reload() {
        this.revisystem.reloadConfig();
        FileConfiguration config = this.revisystem.getConfig();
        this.whitelists = new ArrayList<>(config.getStringList("whitelisted"));
        this.WhitelistEnabled = config.getBoolean("whitelist");
        this.nowhitelistmsg = Utility.TransColor(config.getString("no_whitelisted"));
        Utility.sendConsole(ChatColor.DARK_RED + "[System]" + ChatColor.GRAY + "config neu geladen.");
    }

    public void saveWhitelists() {
        FileConfiguration config = this.revisystem.getConfig();
        config.set("whitelisted", this.whitelists);
        config.set("whitelist", Boolean.valueOf(isWhitelisting()));
        this.revisystem.saveConfig();
    }

    public boolean isWhitelisted(String name) {
        return this.whitelists.contains(name.toLowerCase());
    }

    public void addWhitelist(String name) {
        if (this.whitelists.contains(name.toLowerCase()))
            return;
        this.whitelists.add(name.toLowerCase());
        saveWhitelists();
    }

    public void removeWhitelist(String name) {
        if (!this.whitelists.contains(name.toLowerCase()))
            return;
        this.whitelists.remove(name.toLowerCase());
        saveWhitelists();
    }

    public void setWhitelist(Boolean onoff) {
        this.WhitelistEnabled = onoff.booleanValue();
        saveWhitelists();
    }

    public ArrayList<String> getWhiteLists() {
        return this.whitelists;
    }

    public boolean isWhitelisting() {
        return this.WhitelistEnabled;
    }

    public String getNoWhitelistMsg() {
        return this.nowhitelistmsg;
    }
}

    
    

