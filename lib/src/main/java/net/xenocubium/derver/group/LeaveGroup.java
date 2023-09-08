package net.xenocubium.derver.group;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.xenocubium.derver.group.Group;


public class LeaveGroup implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (args.length < 1) {
			return false;
		}
		
		String group = args[0];
		
    	Player player = (Player) sender;
    	
    	Group groupObj = (new Group());
    	List<String> groups = groupObj.getAllGroups();
    	
    	if (!groups.contains(group)) {
			sender.sendMessage(Component.text("§4Group does not exist!"));
			return true; 
		}
    	
    	groups = groupObj.getGroups(player, false);
    	if (!groups.contains(group)) {
			sender.sendMessage(Component.text("§4You are not in this group!"));
			return true; 
		}
		
		groupObj.addGroup(group, player, true);

		sender.sendMessage(Component.text("§aYou successfully left the group"));
		return true;
	}

}