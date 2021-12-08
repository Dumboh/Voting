package net.nebulamc.voting.cmd.level;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.sender.TypePlayer;
import net.nebulamc.voting.entity.MPlayer;
import org.bukkit.entity.Player;

public class CmdGiveBypass extends MassiveCommand {
    public CmdGiveBypass() {
        this.addAliases("givebypass");

        this.addRequirements(RequirementHasPerm.get("voting.givebypass"));

        this.addParameter(TypePlayer.get(), "player");
    }

    @Override
    public void perform() throws MassiveException {
        Player player = this.readArg();
        MPlayer mp = MPlayer.get(player);
        mp.setBypass(true);
    }
}
