package net.nebulamc.voting;

import com.massivecraft.massivecore.MassivePlugin;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import net.nebulamc.voting.cmd.LevelCommand;
import net.nebulamc.voting.cmd.TokensCommand;
import net.nebulamc.voting.cmd.VoteCommand;
import net.nebulamc.voting.cmd.VoteFlyCommand;
import net.nebulamc.voting.engine.EngineVoteFly;
import net.nebulamc.voting.engine.EngineVoting;
import net.nebulamc.voting.entity.MPlayerColl;
import net.nebulamc.voting.entity.VotingConfColl;
import net.nebulamc.voting.object.Glow;
import net.nebulamc.voting.task.VoteFlyTask;
import net.nebulamc.voting.utils.ItemUtil;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.lang.reflect.Field;

public class Voting extends MassivePlugin {
    private static Voting i;
    public static Voting get() {
        return i;
    }

    @Getter Economy economy;

    @Override
    public boolean isVersionSynchronized() {
        return false;
    }

    @Override
    public void onEnableInner() {
        i = this;
        if (!registerEconomy()) {
            getServer().getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            suicide();
            return;
        }
        registerGlow();
        activate(
                VotingConfColl.class,

                MPlayerColl.class,

                VoteCommand.class,

                VoteFlyCommand.class,

                EngineVoting.class,

                EngineVoteFly.class,

                LevelCommand.class,

                TokensCommand.class,

                VoteFlyTask.class
        );

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private boolean registerEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    private void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Enchantment.registerEnchantment(new Glow(ItemUtil.getGLOW_KEY()));
    }
}
