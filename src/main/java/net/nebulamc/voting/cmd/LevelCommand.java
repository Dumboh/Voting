package net.nebulamc.voting.cmd;

import com.massivecraft.massivecore.chestgui.ChestGui;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import net.nebulamc.voting.action.ActionLevelup;
import net.nebulamc.voting.cmd.level.CmdGiveBypass;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.entity.VotingConf;
import net.nebulamc.voting.entity.object.GuiItem;
import net.nebulamc.voting.entity.object.Level;
import net.nebulamc.voting.utils.ChatUtils;
import net.nebulamc.voting.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LevelCommand extends MassiveCommand {
    private static LevelCommand i = new LevelCommand();
    public static LevelCommand get() {
        return i;
    }

    public LevelCommand() {
        this.addAliases(VotingConf.get().getLevelsCommandAliases());
        this.addChild(new CmdGiveBypass());
        this.addRequirements(RequirementIsPlayer.get());
    }

    @Override
    public void perform() {
        Inventory inventory = Bukkit.createInventory(null, VotingConf.get().getLevelsGuiSize(), ChatUtils.colorizeHex(VotingConf.get().getVoteGuiTitle()));
        ChestGui gui = ChestGui.getCreative(inventory);
        MPlayer mp = MPlayer.get(this.me);
        for (int i = 1; i < VotingConf.get().getLevels().size() + 1; i++) {
            Level level = VotingConf.get().getLevels().get(i);
            GuiItem item = level.getGuiItem();
            ItemStack is = item.build(this.me);
            if (mp.getLevel() >= i) {
                ItemUtil.addGlow(is);
            }
            inventory.setItem(item.getSlot(), is);
            gui.setAction(item.getSlot(), new ActionLevelup(i));
        }
        for (int i = 0; i < VotingConf.get().getLevelsGuiItems().size(); i++) {
            GuiItem item = VotingConf.get().getLevelsGuiItems().get(i);
            inventory.setItem(item.getSlot(), item.build(this.me));
        }
        gui.setSoundClick(null);
        gui.setSoundClose(null);
        gui.setSoundOpen(null);
        this.me.openInventory(gui.getInventory());
    }
}
