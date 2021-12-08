package net.nebulamc.voting.engine;

import com.massivecraft.massivecore.Engine;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class EngineVoteFly extends Engine {
    private static EngineVoteFly i = new EngineVoteFly();
    public static EngineVoteFly get() {
        return i;
    }

    @EventHandler
    public void onFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        MPlayer mp = MPlayer.get(player);
        if (!mp.hasFly() && !player.hasPermission("essentials.fly")) {
            player.setAllowFlight(false);
            player.setFlying(false);
            ChatUtils.sendMultiLineMessage(player, VotingConf.get().getVoteFlyNoMoreTime());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        MPlayer mp = MPlayer.get(player);
        if (!mp.isFlyEnabled()) return;
        mp.setFlyEnabled(false);
        player.setAllowFlight(false);
    }
}
