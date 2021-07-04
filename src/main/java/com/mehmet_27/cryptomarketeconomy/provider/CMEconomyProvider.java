package com.mehmet_27.cryptomarketeconomy.provider;

import com.mehmet_27.cryptomarketeconomy.CryptoMarketEconomy;
import net.brcdev.shopgui.provider.economy.EconomyProvider;
import net.epconsortium.cryptomarket.CryptoMarket;
import net.epconsortium.cryptomarket.database.dao.Balance;
import net.epconsortium.cryptomarket.database.dao.Investor;
import net.epconsortium.cryptomarket.database.dao.InvestorDao;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class CMEconomyProvider extends EconomyProvider {

    InvestorDao investorDao = CryptoMarket.getInstance().getInvestorDao();
    String coinType = CryptoMarketEconomy.getInstance().getEconomyType();

    public CMEconomyProvider() {
        this.currencyPrefix = "";
        this.currencySuffix = " " + coinType;
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
        investorDao.getInvestor(player).getBalance(coinType).setValue(BigDecimal.valueOf(balance + v));
    }

    @Override
    public void withdraw(Player player, double v) {
        double balance = getPlayerBalance(player);
        investorDao.getInvestor(player).getBalance(coinType).setValue(BigDecimal.valueOf(balance - v));
    }

    private Double getPlayerBalance(Player player) {
        Investor investor = investorDao.getInvestor(player);
        Balance balance = investor.getBalance(coinType);
        return balance.getValue().doubleValue();
    }
}


