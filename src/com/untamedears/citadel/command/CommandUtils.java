package com.untamedears.citadel.command;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import com.untamedears.citadel.Citadel;
import com.untamedears.citadel.entity.Faction;
import com.untamedears.citadel.entity.FactionMember;
import com.untamedears.citadel.entity.IReinforcement;
import com.untamedears.citadel.entity.Moderator;
import com.untamedears.citadel.entity.PlayerReinforcement;
import com.untamedears.citadel.manager.GroupManager;
import com.untamedears.citadel.manager.MemberManager;
import com.untamedears.citadel.manager.ReinforcementManager;

public final class CommandUtils {
	public static HashMap<Material,Integer> countReinforcements(String name) {
		HashMap<Material,Integer> hash = new HashMap<Material,Integer>();
		ReinforcementManager reinforcementManager = Citadel.getReinforcementManager();
		Set<IReinforcement> set = reinforcementManager.getReinforcementsByGroup(name);
		Material mat;
		for (IReinforcement r : set) {
			PlayerReinforcement pr = (PlayerReinforcement)r;
			mat = pr.getMaterial().getMaterial();
			if (hash.containsKey(mat)) {
				hash.put(mat, hash.get(mat)+1);
			} else {
				hash.put(mat, 1);
			}
		}
		
		return hash;
	}
	
	public static void printReinforcements(CommandSender sender, String name, HashMap<Material, Integer> reinforcements) {
		sender.sendMessage("Group name: "+name);
		Set<Material> mats = reinforcements.keySet();
		for (Material m : mats) {
			sender.sendMessage(m.name()+": "+reinforcements.get(m));
		}
	}
	
	protected static String joinModeratorSet(Set<Moderator> set) {
		String result = "";
		int size = set.size();
		int i = 0;
		for (Moderator m : set) {
			i++;
			result+=m.getMemberName();
			if (i < size) {
				result+= ", ";
			}
		}
		return result;
	}
	
	protected static String joinMemberSet(Set<FactionMember> set) {
		String result = "";
		int size = set.size();
		int i = 0;
		for (FactionMember m : set) {
			i++;
			result+=m.getMemberName();
			if (i < size) {
				result+= ", ";
			}
		}
		return result;
	}
	
	public static String joinFactionSet(Set<Faction> set) {
		String result = "";
		int size = set.size();
		int i = 0;
		for (Faction f : set) {
			i++;
			result+=f.getName();
			if (i < size) {
				result+= ", ";
			}
		}
		return result;
	}
	
	public static void printGroupMembers(CommandSender sender, String name) {
		GroupManager groupManager = Citadel.getGroupManager();
		Faction group = groupManager.getGroup(name);
		if (group != null) {
			sender.sendMessage("Group name: "+name);
			sender.sendMessage("Admin: "+group.getFounder());
			sender.sendMessage("Moderators: "+joinModeratorSet(groupManager.getModeratorsOfGroup(name)));
			sender.sendMessage("Members: "+joinMemberSet(groupManager.getMembersOfGroup(name)));
		}
	}
}
