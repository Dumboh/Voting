package net.nebulamc.voting.entity.object;

import com.massivecraft.massivecore.store.EntityInternal;
import lombok.Getter;

import java.util.List;

@Getter
public class Perk extends EntityInternal<Perk> {
    private String perkName;
    private List<String> perkRewards;

    public Perk(String perkName, List<String> perkRewards) {
        this.perkName = perkName;
        this.perkRewards = perkRewards;
    }
}
