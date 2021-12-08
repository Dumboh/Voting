package net.nebulamc.voting.utils;

import lombok.Getter;
import net.nebulamc.voting.Voting;
import net.nebulamc.voting.object.Glow;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
    @Getter private static final NamespacedKey GLOW_KEY = new NamespacedKey(Voting.get(), "glow_key");

    public static void addGlow(ItemStack item) {
        ItemMeta im = item.getItemMeta();
        Glow glow = new Glow(ItemUtil.getGLOW_KEY());
        im.addEnchant(glow, 1, true);
        item.setItemMeta(im);
    }
}
