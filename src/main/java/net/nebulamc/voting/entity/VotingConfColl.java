package net.nebulamc.voting.entity;

import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.MStore;
import net.nebulamc.voting.Voting;

public class VotingConfColl extends Coll<VotingConf> {
    private static VotingConfColl i = new VotingConfColl();
    public static VotingConfColl get() {
        return i;
    }

    public VotingConfColl() {
        super("voting_conf", VotingConf.class, MStore.getDb(), Voting.get());
    }

    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        if (!active) return;
        VotingConf.i = this.get("voting-conf", true);
    }
}
