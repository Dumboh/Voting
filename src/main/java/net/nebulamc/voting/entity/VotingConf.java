package net.nebulamc.voting.entity;

import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import lombok.Getter;
import net.nebulamc.voting.entity.object.*;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
public class VotingConf extends Entity<VotingConf> {
    protected static transient VotingConf i;
    public static VotingConf get() {
        return i;
    }

    @Override
    public VotingConf load(@NotNull VotingConf that) {
        super.load(that);
        return this;
    }

    private List<String> voteCommandAliases = MUtil.list("vote", "voting", "votes");

    private String voteGuiTitle = "&a&lVoting";

    private List<GuiItem> voteGuiItems = MUtil.list(
            new GuiItem(
                    Material.PAPER,
                    0,
                    "&7[&dLinks&7]",
                    MUtil.list(
                            "&7&oWe have &nfive&7&o voting links!",
                            "",
                            "&7&oYou must be online on the realm you want",
                            "&7&oto receive your keys on!",
                            "",
                            "&e * Click to view * "
                    ),
                    0
            ),
            new GuiItem(
                    Material.PAPER,
                    0,
                    "&7[&aRewards&7]",
                    MUtil.list(
                            "&6Milestones",
                            "&6&l | &e10 Votes &7| &aDirt",
                            "",
                            "&dLucky Rewards",
                            "&d&l | &fExtra dirt (%4)"
                    ),
                    0
            ),
            new GuiItem(
                    Material.DIRT,
                    0,
                    "&7[&6Statistics&7]",
                    MUtil.list(
                            "",
                            "&aVotes: &f{votes}",
                            "",
                            "&e&lNOTE: &fThis is only for this realm."
                    ),
                    0
            )
    );

    private int voteGuiLinkSlot = 0;

    private List<Milestone> voteMilestones = MUtil.list(
            new Milestone(10, MUtil.list("give {player} dirt"))
    );

    private List<String> voteLinksMessages = MUtil.list("These are our voting links.", "votesiteone.com");

    private List<String> levelsCommandAliases = MUtil.list("levels", "level");

    private String levelsGuiTitle = "&cLevels";

    private int levelsGuiSize = 45;

    private List<GuiItem> levelsGuiItems = MUtil.list(
            new GuiItem(Material.EMERALD, 0, "&aBypass Levels", MUtil.list(""), 33)
    );

    private Map<Integer, Level> levels = MUtil.map(
            1, new Level(
                    50000L,
                    10,
                    MUtil.list(
                            new Perk("15 Total Chest Shops", MUtil.list("lp user {player} perm set chestshops.amount.15"))
                    ),
                    new GuiItem(Material.RED_WOOL, 0, "&cLevel 1", MUtil.list("Lore line 1"), 11)
            ),
            2, new Level(
                    100000L,
                    25,
                    MUtil.list(
                            new Perk("20 Total Chest Shops", MUtil.list("lp user {player} perm set chestshops.amount.20"))
                    ),
                    new GuiItem(Material.RED_WOOL, 0, "&aLevel 2", MUtil.list("Lore line 1"), 12)
            )
    );

    private List<String> levelAlreadyUnlockedMessages = MUtil.list("&c&l(!) &cYou cannot purchase this level because you already have it!");

    private List<String> levelsNeededBeforeLevelup = MUtil.list("&c&l(!) &cYou must levelup to &n{level}&c before purchasing this level.");

    private List<String> levelNotEnoughVotes = MUtil.list("&c&l(!) &cYou must have &n{votes}&c votes in order to purchase this.");

    private List<String> levelNotEnoughMoney = MUtil.list("&c&l(!) &cYou must have $&n{money}&c in order to purchase this.");

    private List<String> levelPurchasedMessages = MUtil.list("&aSuccessfully purchased Level {level} for ${money}");

    private List<String> tokensCommandAliases = MUtil.list("tokens", "token", "votetokens", "vt", "votetoken");

    private List<String> tokensBalanceSelfMessages = MUtil.list("&aYou currently have &n{tokens}&a vote tokens.");

    private List<String> tokensBalanceOtherMessages = MUtil.list("&a{player} currently has &n{tokens}&a vote tokens.");

    private String tokensShopTitle = "&7&lTokens";

    private int tokensShopSize = 9;

    private List<ShopItem> tokensShopItems = MUtil.list(
            new ShopItem(Material.DIRT, 0, "&aShop item 1", MUtil.list("Lore line 1"), MUtil.list("give {player} dirt 1"), 420, 1)
    );

    private List<String> tokensShopNotEnoughTokens = MUtil.list("&cYou do not have enough tokens for this!");

    private List<String> tokensShopSuccessfulPurchase = MUtil.list("&aYou have successfully purchased {item}&a for &n{tokens}&a!");

    private List<String> voteFlyNoMoreTime = MUtil.list("&c&l(!) &cYou no longer have vote fly!");

    private List<String> voteFlyCommandNoTime = MUtil.list(
            "&8&m-----------------------",
            "&cYou do not have any flight",
            "&ctime left. You can get flight",
            "&cby doing blah blah blah.",
            "&8&m-----------------------"
    );

    private List<String> voteFlyCommandToggled = MUtil.list("{color}&l(!) {color}Vote fly {status}.");

    private String voteFlyActionBarMessage = "&aVoteFly enabled &n{seconds}&a left!";

    @Nullable
    public Milestone getMilestone(int votes) {
        Optional<Milestone> oms = voteMilestones.stream().filter(ms -> ms.getNeeded() == votes).findFirst();
        return oms.orElse(null);
    }
}
