package net.nebulamc.voting.cmd.tokens;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import net.nebulamc.voting.cmd.type.TypeMPlayer;
import net.nebulamc.voting.entity.MPlayer;

public class CmdGive extends MassiveCommand {
    public CmdGive() {
        this.addAliases("give");
        this.setDesc("give tokens to a player");

        this.addRequirements(RequirementHasPerm.get("voting.commands.add"));

        this.addParameter(TypeMPlayer.get(), "player");
        this.addParameter(TypeInteger.get(), "amount");
    }

    @Override
    public void perform() throws MassiveException {
        MPlayer target = this.readArg();
        int amount = this.readArg();
        target.addTokens(amount);
    }
}
