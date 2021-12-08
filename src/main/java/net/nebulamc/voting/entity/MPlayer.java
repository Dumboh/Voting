package net.nebulamc.voting.entity;

import com.massivecraft.massivecore.store.SenderEntity;
import lombok.Getter;
import lombok.Setter;
import net.nebulamc.voting.utils.ChatUtils;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class MPlayer extends SenderEntity<MPlayer> {

    protected static transient MPlayer i;
    public static MPlayer get() {
        return i;
    }

    @Getter @Setter private int votes;
    @Getter @Setter private int level;
    @Getter @Setter private int tokens;
    @Getter @Setter private boolean bypass;
    @Getter @Setter private long lastVoted;
    @Getter @Setter private boolean flyEnabled;
    @Getter @Setter private int flyExpiry;


    public MPlayer() {
        this.votes = 0;
        this.level = 0;
        this.tokens = 0;
        this.bypass = false;
        this.flyEnabled = false;
        this.lastVoted = 0;
        this.flyExpiry = 0;
    }

    public static MPlayer get(Object oid) {
        return MPlayerColl.get().get(oid);
    }

    @Override
    public MPlayer load(@NotNull MPlayer that) {
        super.load(that);
        return this;
    }

    public void addVotes(int amount) {
        this.votes += amount;
        this.changed();
    }

    public boolean hasVotes(int amount) {
        return amount <= this.votes;
    }

    public boolean removeVotes(int amount) {
        if (!hasVotes(amount)) return false;

        this.votes -= amount;
        this.changed();
        return true;
    }

    public boolean hasTokens(int amount) {
        return amount <= this.tokens;
    }

    public void addTokens(int amount) {
        this.tokens += amount;
        this.changed();
    }

    public boolean removeTokens(int amount) {
        if (!hasTokens(amount)) return false;

        this.tokens -= amount;
        this.changed();
        return true;
    }

    public boolean hasFly() {
        return this.flyExpiry > 0;
    }

    public void giveFlight(int seconds) {
        this.flyExpiry += seconds;
        this.changed();
    }

    public void tickFly() {
        if (!this.isFlyEnabled()) return;
        this.flyExpiry -= 1;
        if (!this.hasFly()) {
            this.getPlayer().setAllowFlight(false);
            this.getPlayer().setFlying(false);
            this.setFlyEnabled(false);
            ChatUtils.sendMultiLineMessage(this.getPlayer(), VotingConf.get().getVoteFlyNoMoreTime());
        }
        if (this.flyExpiry % 15 == 0) {
            this.changed();
        }
    }
}
