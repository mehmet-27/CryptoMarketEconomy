package com.mehmet_27.cryptomarketeconomy.provider;

import net.brcdev.shopgui.provider.economy.EconomyProvider;
import net.epconsortium.cryptomarket.CryptoMarket;
import net.epconsortium.cryptomarket.database.dao.Balance;
import net.epconsortium.cryptomarket.database.dao.Investor;
import net.epconsortium.cryptomarket.database.dao.InvestorDao;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class CMEconomyProvider extends EconomyProvider {

    InvestorDao investorDao = CryptoMarket.getInstance().getInvestorDao();

    public CMEconomyProvider() {
        this.currencyPrefix = "";
        this.currencySuffix = " BTC";
    }

    @Override
    public String getName() {
        return "CryptoMarketEconomy";
    }

    @Override
    public double getBalance(Player player) {
        return getPlayerBalance(player);
    }

    @Override
    public void deposit(Player player, double v) {
        double balance = getPlayerBalance(player);
        investorDao.getInvestor(player).getBalance("BTC").setValue(BigDecimal.valueOf(balance + v));
    }

    @Override
    public void withdraw(Player player, double v) {
        double balance = getPlayerBalance(player);
        investorDao.getInvestor(player).getBalance("BTC").setValue(BigDecimal.valueOf(balance - v));
    }

    private Double getPlayerBalance(Player player) {
        Investor investor = investorDao.getInvestor(player);
        Balance balance = investor.getBalance("BTC");
        return balance.getValue().doubleValue();
    }
}


