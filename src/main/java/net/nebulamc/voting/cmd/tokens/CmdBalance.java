package net.nebulamc.voting.cmd.tokens;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import net.nebulamc.voting.cmd.type.TypeMPlayer;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.utils.ChatUtils;

public class CmdBalance extends MassiveCommand {
    public CmdBalance() {
        this.addAliases("bal", "balance");

        this.setDesc("get vote token balance");

        this.addParameter(TypeMPlayer.get(), "player", "you");
    }

    @Override
    public void perform() throws MassiveException {
        MPlayer mp = this.readArg(MPlayer.get(sender));
        MPlayer self = MPlayer.get(sender);
        if (mp == self) {
            ChatUtils.sendMultiLineMessage(this.me, VotingConf.get().getTokensBalanceSelfMessages(), "{tokens}", String.valueOf(self.getTokens()));
            return;
        }
        ChatUtils.sendMultiLineMessage(this.me, VotingConf.get().getTokensBalanceOtherMessages(), "{player}", mp.getName(), "{tokens}", String.valueOf(mp.getTokens()));
    }
}
