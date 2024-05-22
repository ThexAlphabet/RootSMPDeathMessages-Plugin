package dev.xalphabet.rootsmpdealth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class RootSMPDealth extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register events
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Entity killer = player.getKiller();

        String deathMessage;

        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            ItemStack weapon = killerPlayer.getInventory().getItemInMainHand();

            if (weapon.hasItemMeta() && weapon.getItemMeta().hasDisplayName()) {
                deathMessage = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "[RootSMP] " + ChatColor.RESET.toString() + ChatColor.RED.toString() + player.getName() + ChatColor.RESET.toString() +
                        " was slain by " + ChatColor.GREEN.toString() + killerPlayer.getName() + ChatColor.RESET.toString() +
                        " using " + ChatColor.YELLOW.toString() + weapon.getItemMeta().getDisplayName();
            } else {
                deathMessage = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "[RootSMP] " + ChatColor.RESET.toString() + ChatColor.RED.toString() + player.getName() + ChatColor.RESET.toString() +
                        " was slain by " + ChatColor.GREEN.toString() + killerPlayer.getName();
            }
        } else {
            deathMessage = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "[RootSMP] " + ChatColor.RESET.toString() + ChatColor.RED.toString() + player.getName() + ChatColor.RESET.toString() +
                    " died!";
        }

        event.setDeathMessage(deathMessage);
    }
}
