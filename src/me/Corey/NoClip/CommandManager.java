package me.Corey.NoClip;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;






public class CommandManager
  implements CommandExecutor
{
  private static CommandManager instance;
  String prefix;
  
  public CommandManager() {
    ChatColor.translateAlternateColorCodes('&', "");
    instance = this;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("noclip"))
      if (sender instanceof Player)
      { Player player = (Player)sender;
        
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
        { if (player.hasPermission("noclip.use"))
            if ((Listeners.getInstance()).main.contains(player.getName())) {
              (Listeners.getInstance()).main.remove(player.getName());
              teleportToSurface(player);
              player.setGameMode(GameMode.CREATIVE);
              player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6NoClip &cdisabled"));
            } else {
              (Listeners.getInstance()).main.add(player.getName());
              player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6NoClip &aenabled"));
            }   }
        else
        { player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Must be in creative to use this command!")); }  }
      else { sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Console cannot execute this command you silly!")); }
       
    return true;
  } public static CommandManager getInstance() {
    return instance;
  } public void teleportToSurface(Player player) {
    int height = 0;
    int maxHeight = player.getWorld().getMaxHeight();

    
    while (height <= maxHeight) {
      height++;
      Block block = player.getLocation().add(0.0D, height, 0.0D).getBlock();
      if (block.isEmpty()) {
        block = player.getEyeLocation().add(0.0D, height, 0.0D).getBlock();
        if (block.isEmpty()) {
          player.teleport(player.getLocation().add(0.0D, height, 0.0D));
          break;
        } 
      } 
    } 
  }
}