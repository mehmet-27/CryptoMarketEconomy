package com.mehmet_27.cryptomarketeconomy;

import com.mehmet_27.cryptomarketeconomy.commands.CMEconomyCommand;
import com.mehmet_27.cryptomarketeconomy.provider.CMEconomyProvider;
import net.brcdev.shopgui.ShopGuiPlusApi;
import org.bukkit.plugin.java.JavaPlugin;

public final class CryptoMarketEconomy extends JavaPlugin {

    private static CryptoMarketEconomy instance;
    private CMEconomyProvider cmEconomyProvider;
    private String coinType;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        instance = this;
        coinType = this.getConfig().getString("coinType");
        this.getLogger().info("The plugin uses " + coinType + " as coin type.");
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

    public static CryptoMarketEconomy getInstance(){
        return instance;
    }
    public String getEconomyType(){
        return coinType;
    }
}
