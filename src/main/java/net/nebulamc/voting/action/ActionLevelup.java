package net.nebulamc.voting.action;

import com.massivecraft.massivecore.chestgui.ChestActionAbstract;
import net.nebulamc.voting.Voting;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.entity.object.Level;
import net.nebulamc.voting.entity.object.Perk;
import net.nebulamc.voting.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ActionLevelup extends ChestActionAbstract {

    private int desiredLevel;

    public ActionLevelup(int desiredLevel) {
        this.desiredLevel = desiredLevel;
    }

    @Override
    public boolean onClick(InventoryClickEvent event, Player player) {
        MPlayer mp = MPlayer.get(player);
        int currentLevel = mp.getLevel();

        if (desiredLevel <= currentLevel) {
            ChatUtils.sendMultiLineMessage(player, VotingConf.get().getLevelAlreadyUnlockedMessages());
            return false;
        }
        if (currentLevel + 1 != desiredLevel) {
            ChatUtils.sendMultiLineMessage(player, VotingConf.get().getLevelsNeededBeforeLevelup(), "{level}", String.valueOf(desiredLevel - 1));
            return false;
        }

        Level level = VotingConf.get().getLevels().get(desiredLevel);

        if (!mp.hasVotes(level.getVotesCost())) {
            ChatUtils.sendMultiLineMessage(player, VotingConf.get().getLevelNotEnoughVotes(), "{votes}", String.valueOf(level.getVotesCost()));
            return false;
        }
        if (!Voting.get().getEconomy().has(player, level.getMoneyCost())) {
            ChatUtils.sendMultiLineMessage(player, VotingConf.get().getLevelNotEnoughMoney(), "{money}", String.valueOf(level.getMoneyCost()));
            return false;
        }
        Voting.get().getEconomy().withdrawPlayer(player, level.getMoneyCost());
        for (Perk perk: level.getPerks()) {
            for (String cmd: perk.getPerkRewards()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("\\{player}", player.getName()));
            }
        }
        mp.setLevel(desiredLevel);
        ChatUtils.sendMultiLineMessage(player, VotingConf.get().getLevelPurchasedMessages(), "{money}", String.valueOf(level.getMoneyCost()), "{level}", String.valueOf(desiredLevel));
        return true;
    }
}
