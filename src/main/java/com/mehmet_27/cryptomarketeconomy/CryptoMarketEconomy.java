package com.mehmet_27.cryptomarketeconomy;

import com.mehmet_27.cryptomarketeconomy.commands.CMEconomyCommand;
import com.mehmet_27.cryptomarketeconomy.provider.CMEconomyProvider;
import net.brcdev.shopgui.ShopGuiPlusApi;
import org.bukkit.plugin.java.JavaPlugin;

public final class CryptoMarketEconomy extends JavaPlugin {

    private CMEconomyProvider cmEconomyProvider;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("cmeconomy").setExecutor(new CMEconomyCommand(this));
        hookIntoShopGui();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void hookIntoShopGui() {
        this.cmEconomyProvider = new CMEconomyProvider();
        ShopGuiPlusApi.registerEconomyProvider(cmEconomyProvider);
    }

    public CMEconomyProvider getMyEconomyProvider() {
        return cmEconomyProvider;
    }
}
