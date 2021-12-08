package net.nebulamc.voting.cmd.type;

import com.massivecraft.massivecore.command.type.Type;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.MPlayerColl;

public class TypeMPlayer {
    public static Type<MPlayer> get() {
        return MPlayerColl.get().getTypeEntity();
    }
}
