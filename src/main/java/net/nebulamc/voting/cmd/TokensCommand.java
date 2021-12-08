package net.nebulamc.voting.cmd;

import com.massivecraft.massivecore.command.MassiveCommand;
import net.nebulamc.voting.cmd.tokens.CmdBalance;
import net.nebulamc.voting.cmd.tokens.CmdGive;
import net.nebulamc.voting.cmd.tokens.CmdShop;
import net.nebulamc.voting.entity.VotingConf;

public class TokensCommand extends MassiveCommand {
    private static TokensCommand i = new TokensCommand();
    public static TokensCommand get() {
        return i;
    }

    public TokensCommand() {
        this.addAliases(VotingConf.get().getTokensCommandAliases());

        this.addChild(new CmdBalance());
        this.addChild(new CmdGive());
        this.addChild(new CmdShop());
    }
}
