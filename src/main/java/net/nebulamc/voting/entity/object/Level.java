package net.nebulamc.voting.entity.object;

import com.massivecraft.massivecore.store.EntityInternal;
import lombok.Getter;

import java.util.List;

@Getter
public class Level extends EntityInternal<Level> {
    private long moneyCost;
    private int votesCost;
    private List<Perk> perks;
    private GuiItem guiItem;

    public Level(long moneyCost, int votesCost, List<Perk> perks, GuiItem guiItem) {
        this.moneyCost = moneyCost;
        this.votesCost = votesCost;
        this.perks = perks;
        this.guiItem = guiItem;
    }
}
