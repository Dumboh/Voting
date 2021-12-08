package net.nebulamc.voting.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import net.nebulamc.voting.cmd.votefly.CmdGive;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.utils.ChatUtils;

public class VoteFlyCommand extends MassiveCommand {

    private static VoteFlyCommand i = new VoteFlyCommand();
    public static VoteFlyCommand get() {
        return i;
    }

    public VoteFlyCommand() {
        this.addAliases("votefly", "vf");

        this.addChild(new CmdGive());
    }

    @Override
    public void perform() {
        MPlayer mp = MPlayer.get(this.me);
        if (!mp.hasFly()) {
            ChatUtils.sendMultiLineMessage(this.me, VotingConf.get().getVoteFlyCommandNoTime());
            return;
        }
        mp.setFlyEnabled(!mp.isFlyEnabled());
        boolean enabled = mp.isFlyEnabled();
        ChatUtils.sendMultiLineMessage(this.me, VotingConf.get().getVoteFlyCommandToggled(),
                "{color}", (enabled ? "&a" : "&c"),
                "{status}", (enabled ? "enabled" : "disabled")
        );
        if (enabled) {
            this.me.setAllowFlight(true);
        }
    }
}
