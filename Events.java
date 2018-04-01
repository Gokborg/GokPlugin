package com.gokborg.gokplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener{
	
	public final GokPlugin gokplugin;
	
	public Events(GokPlugin gokplugin) {
		this.gokplugin = gokplugin;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		Player player = event.getPlayer();
		Block clickedBlock = event.getClickedBlock(); 	
		if(event.getItem() != null) {
			//Display SS
			if(event.getItem().getType() == Material.GOLD_AXE && action.equals(Action.RIGHT_CLICK_BLOCK) && event.getItem().getItemMeta().getDisplayName() == "SS Detector") {
				player.sendMessage(ChatColor.AQUA + "The SS of the " + clickedBlock.getType() + " is " + clickedBlock.getBlockPower()); 
				event.setCancelled(true);
			}
		}
	}
}
	
	
//	
//	@EventHandler
//	public void OnChatEvent(AsyncPlayerChatEvent event) {
//		Player player = event.getPlayer(); //get rid of later
//		String message = event.getMessage();
//		Map<String, Player> dict = new HashMap<>();	
//		//Play sound if name is called
//		Set<Player> s = new HashSet<>();
//		
//		for(Player p: Bukkit.getServer().getOnlinePlayers()){
//				dict.put(p.getName().toLowerCase(), p);
//		}
//		for(String j: message.toLowerCase().split(" ")) {
//			if(dict.containsKey(j)) {
//				s.add(dict.get(j));
//			}
//		}
//		for(Player p: s) {
//			p.sendMessage(ChatColor.RED + player.getName() + " said ur name nub.");
//			p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 10f, 1f);
//		}
//		
//		//New Function Goes HERE
//	}
//}
	