package net.nebulamc.voting.task;

import com.massivecraft.massivecore.ModuloRepeatTask;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class VoteFlyTask extends ModuloRepeatTask {

    private static VoteFlyTask i = new VoteFlyTask();
    public static VoteFlyTask get() {
        return i;
    }

    @Override
    public long getDelayMillis() {
        return TimeUnit.SECONDS.toMillis(1);
    }

    @Override
    public void invoke(long l) {
        for (Player player: Bukkit.getOnlinePlayers()) {
            MPlayer mp = MPlayer.get(player);
            if (!mp.isFlyEnabled()) return;
            mp.tickFly();
            if (mp.isFlyEnabled()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtils.colorize(VotingConf.get().getVoteFlyActionBarMessage(), "{seconds}", String.valueOf(mp.getFlyExpiry()))));
            }
        }
    }
}
