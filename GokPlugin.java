package com.gokborg.gokplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.ecconia.rsisland.plugin.selection.api.ISelection;
import com.ecconia.rsisland.plugin.selection.api.SelectionAPI;

public class GokPlugin extends JavaPlugin {
	
	private SelectionAPI selection;
	//Cool Msg Class
	private Feedback f; 
	
	@Override 
	public void onEnable() 
	{
		f = new Feedback(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "GokPlugin" + ChatColor.GRAY + "] ");
		getServer().getPluginManager().registerEvents(new Events(this), this);
		RegisteredServiceProvider<SelectionAPI> provider = getServer().getServicesManager().getRegistration(SelectionAPI.class);
		if (provider != null) {
			selection = provider.getProvider();
		}
		else {
			getLogger().severe("Download Selection Plugin on server: https://github.com/RSIsland/SelectionPlugin OR contact ASRS_");
			onDisable();
		}
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if(label.equalsIgnoreCase("ifloss") && sender instanceof Player && sender.hasPermission("gokplugin.permission")) {
			sender.sendMessage("uraliar " + sender.getName());
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("up") && sender instanceof Player && sender.hasPermission("gokplugin.permission")) {
			Player player = (Player) sender; 
			Block block = player.getLocation().subtract(0, 1, 0).getBlock();
			block.setType(Material.GLASS);
			
			return true;
		}
		//FILL COMMAND
		if(label.equalsIgnoreCase("fill") && sender instanceof Player && sender.hasPermission("gokplugin.permission")) {
			Player player = (Player) sender; 
			ISelection selinterface = selection.getPlayer(player).getSelectionOrCurrent("gokplugin");
			if (selinterface == null) {
				f.e(player, "You must make a selection!");
			}
			else if (args.length != 1) {
				f.e(player, "Please put a material and thats it!");
			}
			
			else {
				
				Material fillMaterial = Material.matchMaterial(args[0]);
				
				if (fillMaterial == null) {
					f.e(player, "Invalid block type!");
				}
				else {
					World world = player.getWorld();
		     		Location pos1 = selinterface.getMinPoint();
		     		Location pos2 = selinterface.getMaxPoint();
		     		int x1 = pos1.getBlockX();
		     		int y1 = pos1.getBlockY();
		    		int z1 = pos1.getBlockZ();
		    		int x2 = pos2.getBlockX();
		    		int y2 = pos2.getBlockY();
		     		int z2 = pos2.getBlockZ();
	
					for(int x = x1; x <= x2; x++) {
						for(int z = z1; z <= z2; z++) {
							for(int y = y1; y <= y2; y++) {
								world.getBlockAt(x, y, z).setType(fillMaterial);
							}
						}
					}
					f.n(player, "Area has been filled!");
			}
		
		}
    		
		return true;
	}
		
		if(label.equalsIgnoreCase("replace") && sender instanceof Player && sender.hasPermission("gokplugin.permission")){
			
			Player player = (Player) sender;
			ISelection selinterface = selection.getPlayer(player).getSelectionOrCurrent("gokplugin");
			if(args.length != 2) {
				f.e(player, "Two arguments required!");
			}
			else if (Material.matchMaterial(args[0]) == null || Material.matchMaterial(args[1]) == null) {
				f.e(player,  "Invalid arguments!");
			}
			else if (selinterface == null){
				f.e(player, "You must make a selection!");
			}
			else {
				World world = player.getWorld();
	     		Location pos1 = selinterface.getMinPoint();
	     		Location pos2 = selinterface.getMaxPoint();
	     		int x1 = pos1.getBlockX();
	     		int y1 = pos1.getBlockY();
	    		int z1 = pos1.getBlockZ();
	    		int x2 = pos2.getBlockX();
	    		int y2 = pos2.getBlockY();
	     		int z2 = pos2.getBlockZ();
				for(int x = x1; x <= x2; x++) {
					for(int z = z1; z <= z2; z++) {
						for(int y = y1; y <= y2; y++) {
							player.sendMessage(world.getBlockAt(x, y, z).getType().toString());
							player.sendMessage(Material.matchMaterial(args[0]).toString());
							if (world.getBlockAt(x, y, z).getType() == Material.matchMaterial(args[0])) {
								world.getBlockAt(x, y, z).setType(Material.matchMaterial(args[1]));
							}
						}
					}
				}
				f.n(player, "Replace complete!");
			}
			return true;
		}
		
		
		//TODO: Copy command
		if(label.equalsIgnoreCase("copy") && sender instanceof Player && sender.hasPermission("gokplugin.permission")) {
			
			Player player = (Player) sender; 
			ISelection selinterface = selection.getPlayer(player).getSelectionOrCurrent("gokplugin");
			if (selinterface == null) {
				f.e(player, "%v , you must make a selection!", player.getName());
				
			}
			else {
				f.n(player, "Your good!");
				
			}
			
			
			
			return true;
		}
		//TODO: Paste command
		if(label.equalsIgnoreCase("paste") && sender instanceof Player && sender.hasPermission("gokplugin.permission")) {
			return false;
		}
		
		
		if(label.equalsIgnoreCase("ss") && sender instanceof Player && sender.hasPermission("gokplugin.permssion")) {
			Player p = (Player) sender;
			ItemStack is = new ItemStack(Material.GOLD_AXE);
			ItemMeta i = is.getItemMeta();
			i.setDisplayName("SS Detector");
			is.setItemMeta(i);
			p.getInventory().addItem(is);
			return true;
		}
		
		return false;
	}
	

}
