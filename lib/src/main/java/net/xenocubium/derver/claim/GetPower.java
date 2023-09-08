package net.xenocubium.derver.claim;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.xenocubium.derver.group.Group;


public class GetPower implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {

		if (args.length < 1) return false;
		
		String group = args[0];
		
		File powerFile = new File("power.yaml");
    	if (!powerFile.exists()) {
    		try {
    			powerFile.createNewFile();
    		} catch (IOException e) {
                e.printStackTrace();
            }
    	}
    	
    	Group groupObj = (new Group());
    	List<String> groups = groupObj.getAllGroups();
    	
    	if (!groups.contains(group)) {
			sender.sendMessage(Component.text("§4Group does not exist!"));
			return true; 
		}
    	
    	YamlConfiguration config = YamlConfiguration.loadConfiguration(powerFile);

    	int amount = config.getInt(group, 0);
    	
    	sender.sendMessage(Component.text("The score of §l" + group + "§r is §l" +  amount));
		return true;
	}

}