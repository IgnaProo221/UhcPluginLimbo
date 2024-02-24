package net.ignaproo.uhcplugin.Events;

import net.ignaproo.uhcplugin.Config.ConfigData;
import net.ignaproo.uhcplugin.Utils.Ranks;
import net.ignaproo.uhcplugin.Utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Player target = (Player) event.getEntity();

            // Obtener el equipo de cada jugador
            Ranks.Rangos attackerRank = Ranks.getRank(attacker);
            Ranks.Rangos targetRank = Ranks.getRank(target);

            // Verificar si los jugadores están en el mismo equipo
            if (ConfigData.isOnGame().equalsIgnoreCase("false")) {
                event.setCancelled(true);
                return;
            } else {
                if (attackerRank == targetRank) {
                    event.setCancelled(true);
                    event.getDamager().sendMessage(Utils.getPrefix() + "&cNo puedes golpear a tu equipo.");
                    ((Player) event.getDamager()).playSound(event.getDamager().getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.2F);
                } else {
                    if (ConfigData.pvpEnable().equalsIgnoreCase("false")) {
                        event.getDamager().sendMessage(Utils.getPrefix() + "&cEl pvp esta desactivado.");
                        ((Player) event.getDamager()).playSound(event.getDamager().getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.2F);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
