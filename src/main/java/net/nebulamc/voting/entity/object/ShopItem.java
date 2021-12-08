package net.nebulamc.voting.entity.object;


import com.massivecraft.massivecore.store.EntityInternal;
import lombok.Getter;
import net.nebulamc.voting.utils.ChatUtils;
import net.nebulamc.voting.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public class ShopItem extends EntityInternal<ShopItem> {
    private Material material;
    private int data;
    private String name;
    private List<String> lore;
    private List<String> commands;
    private int price;
    private int slot;

    public ShopItem(Material material, int data, String name, List<String> lore, List<String> commands, int price, int slot) {
        this.material = material;
        this.data = data;
        this.name = name;
        this.lore = lore;
        this.commands = commands;
        this.price = price;
        this.slot = slot;
    }

    public ItemStack createItem() {
        return new ItemBuilder(this.material).durability(this.data).name(ChatUtils.colorize(this.name)).loar(ChatUtils.colorizeList(this.lore, "{price}", String.valueOf(price)));
    }
}
