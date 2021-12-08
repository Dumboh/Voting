package net.nebulamc.voting.cmd.tokens;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.chestgui.ChestGui;
import com.massivecraft.massivecore.command.MassiveCommand;
import net.nebulamc.voting.action.ActionShop;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.entity.object.ShopItem;
import net.nebulamc.voting.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CmdShop extends MassiveCommand {
    public CmdShop() {
        this.addAliases("shop");
        this.setDesc("purchase shop items using vote tokens");
    }

    @Override
    public void perform() throws MassiveException {
        Inventory inventory = Bukkit.createInventory(null, VotingConf.get().getTokensShopSize(), ChatUtils.colorize(VotingConf.get().getTokensShopTitle()));
        ChestGui gui = ChestGui.getCreative(inventory);
        gui.setAutoclosing(true);
        List<ShopItem> shopitems = VotingConf.get().getTokensShopItems();
        for (int i = 0; i < shopitems.size(); i++) {
            ShopItem shopItem = shopitems.get(i);
            ItemStack item = shopItem.createItem();
            inventory.setItem(shopItem.getSlot(), item);
            gui.setAction(shopItem.getSlot(), new ActionShop(shopItem));
        }
        this.me.openInventory(gui.getInventory());
    }
}
