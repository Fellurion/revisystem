package de.fellurion.revisystem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onleave implements Listener 
{

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent leave) {
        leave.getPlayer().setOp(false);
    }
    
    
    
}
