package net.xenocubium.derver.claim;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.xenocubium.derver.group.Group;

public class Power implements CommandExecutor {

	Material cost = Material.DIAMOND;
	Material cost2 = Material.DIAMOND_BLOCK;
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (!(sender instanceof Player)) return false;
		
		if (args.length < 1) return false;
		
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
		
		Inventory inv = player.getInventory();
		ItemStack[] contents = inv.getContents();
		inv.remove(cost);
		inv.remove(cost2);
		
		int amount = 0;
		for (int i = 0; i < contents.length; i++) {
			ItemStack content = contents[i];
			if (content == null) continue;
			if (content.getType() == cost) {
				amount += content.getAmount();
			}
			if (content.getType() == cost2) {
				amount += content.getAmount() * 9;
			}
		}
		
		amount *= 5;
		
    	PowerThing pthing = new PowerThing(new Group());
    	
    	pthing.addPower(group, amount);
    	
    	player.sendMessage(Component.text("The group score has been updated by §l" +  amount));
		
		return true;
	}

}