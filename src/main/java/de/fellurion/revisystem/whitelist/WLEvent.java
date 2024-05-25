package de.fellurion.revisystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class WLEvent implements Listener
{
    private Revisystem revisystem;

    public WLEvent(Revisystem m) {
        this.revisystem = m;
    }

    @EventHandler
    public void onConnect(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if (p == null)
            return;
        if (!this.revisystem.getStorage().isWhitelisting())
            return;
        if (this.revisystem.getStorage().isWhitelisted(p.getName()))
            return;
        String msg = this.revisystem.getStorage().getNoWhitelistMsg();
        e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, this.revisystem.getStorage().getNoWhitelistMsg());
    }
    
    
    
    
}

