package net.nebulamc.voting.entity.object;

import com.massivecraft.massivecore.store.EntityInternal;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
public class Milestone extends EntityInternal<Milestone> {
    private int needed;
    private List<String> rewards;

    public Milestone(int needed, List<String> rewards) {
        this.needed = needed;
        this.rewards = rewards;
    }

    public void reward(Player player) {
        for (String reward: rewards) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward.replaceAll("\\{player}", player.getName()));
        }
    }
}
