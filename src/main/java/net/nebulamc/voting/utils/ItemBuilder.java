package net.nebulamc.voting.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder extends ItemStack {
    public ItemBuilder(Material mat) {
        super(mat);
    }

    public ItemBuilder name(String name) {
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(name);
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder loar(List<String> texts) {
        ItemMeta meta = this.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.addAll(texts);
        meta.setLore(lore);
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder durability(int durability) {
        this.setDurability((short)durability);
        return this;
    }
}
