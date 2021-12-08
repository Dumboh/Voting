package net.nebulamc.voting.action;

import com.massivecraft.massivecore.chestgui.ChestActionAbstract;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.entity.object.ShopItem;
import net.nebulamc.voting.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ActionShop extends ChestActionAbstract {
    public ShopItem shopItem;

    public ActionShop(ShopItem shopItem) {
        this.shopItem = shopItem;
    }

    @Override
    public boolean onClick(InventoryClickEvent event, Player player) {
        MPlayer mplayer = MPlayer.get(player);
        int price = shopItem.getPrice();
        if (!mplayer.removeTokens(price)) {
            ChatUtils.sendMultiLineMessage(player, VotingConf.get().getTokensShopNotEnoughTokens(), "{item}", ChatUtils.colorize(shopItem.getName()));
            return false;
        }
        ChatUtils.sendMultiLineMessage(player, VotingConf.get().getTokensShopSuccessfulPurchase(), "{item}", ChatUtils.colorize(shopItem.getName()), "{tokens}", String.valueOf(price));
        shopItem.getCommands().forEach(cmd -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("\\{player}", player.getName())));
        return true;
    }
}
