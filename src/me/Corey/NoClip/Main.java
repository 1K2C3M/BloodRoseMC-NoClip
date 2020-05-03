package me.Corey.NoClip;
 
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  private static Main instance;
  
  public Main() {
	  instance = this;
	  }
  
  public static Main getPlugin() {
    return instance;
  }
  
  public void onEnable() {
	  Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), (Plugin)this);
	  getCommand("noclip").setExecutor(new CommandManager());
  }
 
   public void onDisable() {
    for (String pName : (Listeners.getInstance()).main) {
      Player player = Bukkit.getPlayer(pName);
      
      player.setGameMode(GameMode.CREATIVE);
      CommandManager.getInstance().teleportToSurface(player);
      (Listeners.getInstance()).main.remove(pName);
    } 
  }
}