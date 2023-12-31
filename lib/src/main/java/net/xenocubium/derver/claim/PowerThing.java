package net.xenocubium.derver.claim;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import net.xenocubium.derver.group.Group;

public class PowerThing {
	YamlConfiguration config;
	
	String file = "power.yaml";
	Group groupObj;
	
	public PowerThing(Group groupObj) {
		File powerFile = new File(file);
    	if (!powerFile.exists()) {
    		try {
    			powerFile.createNewFile();
    		} catch (IOException e) {
                e.printStackTrace();
            }
    	}
    	
    	this.config = YamlConfiguration.loadConfiguration(powerFile);
    	
    	this.groupObj = groupObj;
	}
	
	public void addPower(String group, int amount) {
		amount += config.getInt(group, 0);
		
		if (amount < 0) {
			groupObj.deleteGroup(group);
		} else {
	    	config.set(group,amount);
		}
    	try {
			config.save("power.yaml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getPower(String group) {
		return config.getInt(group, 0);
	}
}