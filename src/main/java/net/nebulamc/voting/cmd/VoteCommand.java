package net.nebulamc.voting.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.chestgui.ChestGui;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.entity.object.GuiItem;
import net.nebulamc.voting.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class VoteCommand extends MassiveCommand {
    private static VoteCommand i = new VoteCommand();
    public static VoteCommand get() {
        return i;
    }

    public VoteCommand() {
        this.addAliases(VotingConf.get().getVoteCommandAliases());
        this.addRequirements(RequirementIsPlayer.get());
    }

    @Override
    public void perform() throws MassiveException {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER);
        ChestGui gui = ChestGui.getCreative(inventory);
        for (int i = 0; i < VotingConf.get().getVoteGuiItems().size(); i++) {
            GuiItem item = VotingConf.get().getVoteGuiItems().get(i);
            inventory.setItem(item.getSlot(), item.build(this.me));
        }
        gui.setAction(VotingConf.get().getVoteGuiLinkSlot(), (event) -> {
            if (!(event.getWhoClicked() instanceof Player player)) return false;
            ChatUtils.sendMultiLineMessage(player, VotingConf.get().getVoteLinksMessages());
            player.closeInventory();
            return true;
        });
        gui.setSoundClose(null);
        gui.setSoundOpen(null);
        gui.setSoundClick(null);
        this.me.openInventory(gui.getInventory());
    }
}
