package com.mehmet_27.cryptomarketeconomy.commands;

import com.mehmet_27.cryptomarketeconomy.CryptoMarketEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMEconomyCommand implements CommandExecutor {
    private CryptoMarketEconomy plugin;

    public CMEconomyCommand(CryptoMarketEconomy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can be used only in game.");
            return true;
        }

        Player player = (Player) sender;
        sender.sendMessage("Your balance is " + plugin.getMyEconomyProvider().getBalance(player) + "BTC.");
        return true;
    }
}
