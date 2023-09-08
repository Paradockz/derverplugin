package net.xenocubium.derver.group;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.xenocubium.derver.group.Group;


public class NewGroup implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (args.length < 1) {
			return false;
		}
		
		String group = args[0];
		
		String pattern= "^[a-zA-Z0-9_]*$";
		
		if (!group.matches(pattern)) {
			sender.sendMessage(Component.text("§4Group name must only contain alphanumeric characters and underscores!"));
			return true;
		}
    	
    	Player player = (Player) sender;
    	Group groupObj = (new Group());
    	List<String> groupie = groupObj.getGroup(group);
		
		if (groupie != null && groupie.size() > 0) {
			sender.sendMessage(Component.text("§4This group already exist!"));
			return true;
		}
		
		groupObj.addGroup(group, player,false);

		sender.sendMessage(Component.text("§aSuccessfully created §a§l" + group + "!"));
		return true;
	}

}