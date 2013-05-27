package com.untamedears.citadel.command.commands;

import org.bukkit.command.CommandSender;

import com.untamedears.citadel.Citadel;
import com.untamedears.citadel.command.PlayerCommand;
import com.untamedears.citadel.manager.ReinforcementManager;

public class StatsCommand extends PlayerCommand {

	public StatsCommand() {
		super("View Stats");
		setDescription("View citadel stats");
		setUsage("/ctstats");
		setIdentifiers(new String[] {"ctstats"});
	}

	public boolean execute(CommandSender sender, String[] args) {		
		ReinforcementManager reinforcementManager = Citadel.getReinforcementManager();
		int numReinforcements = reinforcementManager.getReinforcementsAmount();		
		sender.sendMessage(new StringBuilder().append("§cTotal Reinforcements:§e " ).append(numReinforcements).toString());
		return true;
	}

}
