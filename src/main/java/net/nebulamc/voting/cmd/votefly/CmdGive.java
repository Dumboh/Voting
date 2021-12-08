package net.nebulamc.voting.cmd.votefly;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import net.nebulamc.voting.cmd.type.TypeMPlayer;
import net.nebulamc.voting.entity.MPlayer;

public class CmdGive extends MassiveCommand {

    public CmdGive() {
        this.addAliases("give");
        this.setDesc("give player votefly");
        this.addRequirements(RequirementHasPerm.get("voting.votefly.give"));
        this.addParameter(TypeMPlayer.get(), "player");
        this.addParameter(TypeInteger.get(), "seconds");
    }

    @Override
    public void perform() throws MassiveException {
        MPlayer mp = this.readArg();
        int seconds = this.readArg();
        mp.giveFlight(seconds);
        msg("<i>&aSuccessfully gave \"%s\" vote fly for &n%d&a seconds.", mp.getName(), seconds);
    }
}
