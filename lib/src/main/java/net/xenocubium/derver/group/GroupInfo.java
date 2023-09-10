package net.xenocubium.derver.group;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.xenocubium.derver.group.Group;

public class GroupInfo implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {

		if (args.length < 1) return false;
		
		Group groupObj = (new Group());
		
		String group = args[0];
		
		List<String> groups = groupObj.getAllGroups();
    	
    	if (!groups.contains(group)) {
			sender.sendMessage(Component.text("§4Group does not exist!"));
			return true; 
		}
    	
    	File powerFile = new File("power.yaml");
    	if (!powerFile.exists()) {
    		try {
    			powerFile.createNewFile();
    		} catch (IOException e) {
                e.printStackTrace();
            }
    	}
    	
    	YamlConfiguration config = YamlConfiguration.loadConfiguration(powerFile);

    	int amount = config.getInt(group, 0);
    	
    	sender.sendMessage(Component.text("§6This are the informations for §c§l" + group + "§6:"));
    	
    	sender.sendMessage(Component.text("§6Score: §c§l" +  amount));
		
    	List<String> groupInfo = groupObj.getGroup(group);
    	
    	for (int i = 0; i < groupInfo.size(); i++) {
    		String user = groupInfo.get(i);
    		UUID uuid = UUID.fromString(user);
    		OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
    		
    		groupInfo.set(i,(player == null) ? "Unknown" : player.getName());
    	}
    	
    	String groupConcat = String.join("\n§6- §c§l", groupInfo);
    	
    	sender.sendMessage(Component.text("§6Members:\n- " +  groupConcat));
		return true;
	}

}
