package net.nebulamc.voting.engine;

import com.massivecraft.massivecore.Engine;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.entity.object.Milestone;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class EngineVoting extends Engine {

    private static EngineVoting i = new EngineVoting();
    public static EngineVoting get() {
        return i;
    }

    @EventHandler
    public void onVote(VotifierEvent event) {
        Vote vote = event.getVote();
        Player player = Bukkit.getPlayer(vote.getUsername());
        if (player == null) return;

        MPlayer mp = MPlayer.get(player);
        mp.addVotes(1);
        mp.addTokens(1);
        mp.setLastVoted(System.currentTimeMillis());
        Milestone milestone = VotingConf.get().getMilestone(mp.getVotes());
        if (milestone != null) {
            milestone.reward(player);
        }
    }
}
