package me.Corey.NoClip;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;





public class Listeners
  implements Listener
{
  private static Listeners instance;
  public ArrayList<String> main;
  private final BlockFace[] surrounding;
  
  public Listeners() {
    this.main = new ArrayList<>();






































    
    this





      
      .surrounding = new BlockFace[] { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };
    instance = this;
  } public boolean nearBlock(Player player) {
    boolean nearBlock = false;
    Location[] locs = { player.getLocation(), 
        player.getLocation().add(0.0D, 1.0D, 0.0D), 
        player.getLocation().add(0.0D, 2.0D, 0.0D) }; byte b; int i;
    Location[] arrayOfLocation1;
    for (i = (arrayOfLocation1 = locs).length, b = 0; b < i; ) { Location loc = arrayOfLocation1[b]; byte b1; int j; BlockFace[] arrayOfBlockFace;
      for (j = (arrayOfBlockFace = this.surrounding).length, b1 = 0; b1 < j; ) { BlockFace bf = arrayOfBlockFace[b1];
        if (!loc.getBlock().getRelative(bf, 1).isEmpty()) nearBlock = true;  b1++; }
      
      b++; }
    
    if (!player.getLocation().add(0.0D, 2.0D, 0.0D).getBlock().isEmpty()) nearBlock = true; 
    if (!player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().isEmpty()) nearBlock = true;
    
    return nearBlock;
  }
  
  public static Listeners getInstance() {
    return instance;
  }
  
  @EventHandler
  public void onNearBlock(PlayerMoveEvent e) {
    Player player = e.getPlayer();
    if ((player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) && this.main.contains(player.getName()))
      if (nearBlock(player)) {
        player.setGameMode(GameMode.SPECTATOR);
      } else {
        player.setGameMode(GameMode.CREATIVE);
      }  
  }
  
  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent e) {
    Player player = e.getPlayer();
    if (this.main.contains(player.getName())) {
      this.main.remove(player.getName());
      player.setGameMode(GameMode.CREATIVE);
      CommandManager.getInstance().teleportToSurface(player);
    } 
  }
  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent e) {
    Player player = e.getEntity();
    if (this.main.contains(player.getName())) {
      this.main.remove(player.getName());
      player.setGameMode(GameMode.CREATIVE);
    } 
  }
}