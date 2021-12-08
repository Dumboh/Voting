package net.nebulamc.voting.entity.object;

import com.massivecraft.massivecore.store.EntityInternal;
import lombok.Getter;
import net.nebulamc.voting.entity.MPlayer;
import net.nebulamc.voting.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter
public class GuiItem extends EntityInternal<GuiItem> {
    private Material material;
    private int damage;
    private String displayName;
    private List<String> lore;
    private int slot;

    public GuiItem(Material material, int damage, String displayName, List<String> lore, int slot) {
        this.material = material;
        this.damage = damage;
        this.displayName = displayName;
        this.lore = lore;
        this.slot = slot;
    }

    public ItemStack build(Player player) {
        MPlayer mp = MPlayer.get(player);
        ItemStack item = new ItemStack(this.material, 1, (short) this.damage);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatUtils.colorizeHex(this.displayName));
        im.setLore(ChatUtils.colorizeList(this.lore, "{votes}", String.valueOf(mp.getVotes())));
        item.setItemMeta(im);
        return item;
    }
}
