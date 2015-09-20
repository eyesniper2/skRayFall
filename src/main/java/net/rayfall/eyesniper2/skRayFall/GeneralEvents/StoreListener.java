package net.rayfall.eyesniper2.skRayFall.GeneralEvents;

import java.util.ArrayList;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class StoreListener implements Listener {

	private ArrayList<Player> StorePossible = new ArrayList<>();
	private ArrayList<Player> UnstorePossible = new ArrayList<>();

	public StoreListener(skRayFall core) {
		core.getServer().getPluginManager().registerEvents(this, core);
	}

	@EventHandler
	public void onStoringFilter(InventoryClickEvent evt) {
		if (evt.getAction() == InventoryAction.SWAP_WITH_CURSOR
				|| evt.getAction() == InventoryAction.COLLECT_TO_CURSOR
				|| evt.getAction() == InventoryAction.PICKUP_SOME
				|| evt.getAction() == InventoryAction.PICKUP_HALF
				|| evt.getAction() == InventoryAction.PICKUP_ALL
				|| evt.getAction() == InventoryAction.PICKUP_ONE) {
			if (evt.getView().getBottomInventory().getType() == InventoryType.PLAYER
					&& (evt.getView().getTopInventory().getType() == InventoryType.CHEST
							|| evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
							|| evt.getView().getTopInventory().getType() == InventoryType.HOPPER
							|| evt.getView().getTopInventory().getType() == InventoryType.DISPENSER || evt
							.getView().getTopInventory().getType() == InventoryType.DROPPER)
					&& evt.getClickedInventory().getType() == InventoryType.PLAYER
					&& (!(StorePossible.contains((Player) evt.getWhoClicked())))) {
				StorePossible.add((Player) evt.getWhoClicked());
				if (UnstorePossible.contains((Player) evt.getWhoClicked())) {
					UnstorePossible.remove((Player) evt.getWhoClicked());
				}
			}
			if (evt.getView().getBottomInventory().getType() == InventoryType.PLAYER
					&& (evt.getView().getTopInventory().getType() == InventoryType.CHEST
							|| evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
							|| evt.getView().getTopInventory().getType() == InventoryType.HOPPER
							|| evt.getView().getTopInventory().getType() == InventoryType.DISPENSER || evt
							.getView().getTopInventory().getType() == InventoryType.DROPPER)
					&& (evt.getClickedInventory().getType() == InventoryType.CHEST
							|| evt.getClickedInventory().getType() == InventoryType.ENDER_CHEST
							|| evt.getClickedInventory().getType() == InventoryType.HOPPER
							|| evt.getClickedInventory().getType() == InventoryType.DISPENSER || evt
							.getClickedInventory().getType() == InventoryType.DROPPER)
					&& (!(UnstorePossible
							.contains((Player) evt.getWhoClicked())))) {
				UnstorePossible.add((Player) evt.getWhoClicked());
				if (StorePossible.contains((Player) evt.getWhoClicked())) {
					StorePossible.remove((Player) evt.getWhoClicked());
				}
			}
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent evt) {
		if (evt.getView().getBottomInventory().getType() == InventoryType.PLAYER
				&& (evt.getView().getTopInventory().getType() == InventoryType.CHEST
						|| evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
						|| evt.getView().getTopInventory().getType() == InventoryType.HOPPER
						|| evt.getView().getTopInventory().getType() == InventoryType.DISPENSER || evt
						.getView().getTopInventory().getType() == InventoryType.DROPPER)) {
			if (StorePossible.contains((Player) evt.getPlayer())) {
				StorePossible.remove((Player) evt.getPlayer());
			} else if (UnstorePossible.contains((Player) evt.getPlayer())) {
				UnstorePossible.remove((Player) evt.getPlayer());
			}
		}
	}

	@EventHandler
	public void onStore(InventoryClickEvent evt) {
		if ((evt.getAction() == InventoryAction.PLACE_ONE
				|| evt.getAction() == InventoryAction.PLACE_SOME
				|| evt.getAction() == InventoryAction.PLACE_ALL
				|| evt.getAction() == InventoryAction.SWAP_WITH_CURSOR) && evt.getClickedInventory() != null) {
			if (evt.getClickedInventory().getType() == InventoryType.CHEST
					|| evt.getClickedInventory().getType() == InventoryType.ENDER_CHEST
					|| evt.getClickedInventory().getType() == InventoryType.HOPPER
					|| evt.getClickedInventory().getType() == InventoryType.DISPENSER
					|| evt.getClickedInventory().getType() == InventoryType.DROPPER) {
				if (evt.isLeftClick()
						&& StorePossible.contains((Player) evt.getWhoClicked())) {
					StoreEvent event = new StoreEvent(
							(Player) evt.getWhoClicked(), evt.getCursor()
									.clone(), evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
						return;
					}
					StorePossible.remove((Player) evt.getWhoClicked());
				}
				if (evt.isRightClick()
						&& StorePossible.contains((Player) evt.getWhoClicked())) {
					ItemStack i = evt.getCursor().clone();
					i.setAmount(1);
					StoreEvent event = new StoreEvent(
							(Player) evt.getWhoClicked(), i, evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
					}
				}
			}
		}
		if (evt.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
			if (evt.isShiftClick()
					&& evt.getClickedInventory().getType() == InventoryType.PLAYER) {
				if ((evt.getView().getTopInventory().getType() == InventoryType.CHEST
						|| evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
						|| evt.getView().getTopInventory().getType() == InventoryType.HOPPER
						|| evt.getView().getTopInventory().getType() == InventoryType.DISPENSER || evt
						.getView().getTopInventory().getType() == InventoryType.DROPPER)
						&& evt.getView().getBottomInventory().getType() == InventoryType.PLAYER) {
					StoreEvent event = new StoreEvent(
							(Player) evt.getWhoClicked(), evt.getCurrentItem()
									.clone(), evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
						return;
					}
					StorePossible.remove((Player) evt.getWhoClicked());
				}
			}
		}
	}

	@EventHandler
	public void onStoreDrag(InventoryDragEvent evt) {
		if ((evt.getInventory().getType() == InventoryType.CHEST
				|| evt.getInventory().getType() == InventoryType.ENDER_CHEST
				|| evt.getInventory().getType() == InventoryType.HOPPER
				|| evt.getInventory().getType() == InventoryType.DISPENSER || evt
				.getInventory().getType() == InventoryType.DROPPER)
				&& StorePossible.contains((Player) evt.getWhoClicked())) {
			int chestSize = evt.getView().getTopInventory().getSize();
			int num = 0;
			ItemStack m = new ItemStack(Material.AIR, 0);
			for (int i : evt.getNewItems().keySet()) {
				if (!(i >= chestSize)) {
					ItemStack item = evt.getNewItems().get(i);
					if (num == 0) {
						m = item.clone();
					}
					if (evt.getInventory().getItem(i) != null) {
						num += item.getAmount()
								- evt.getInventory().getItem(i).getAmount();
					} else {
						num += item.getAmount();
					}
				}
			}
			if (m.getType() == Material.AIR) {
				return;
			}
			m.setAmount(num);
			StoreEvent event = new StoreEvent((Player) evt.getWhoClicked(), m, evt.getInventory());
			Bukkit.getPluginManager().callEvent(event);
			if (event.isCancelled()) {
				evt.setCancelled(true);
			}
		}
		if ((evt.getView().getBottomInventory().getType() == InventoryType.PLAYER && (evt
				.getView().getTopInventory().getType() == InventoryType.CHEST
				|| evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
				|| evt.getView().getTopInventory().getType() == InventoryType.HOPPER
				|| evt.getView().getTopInventory().getType() == InventoryType.DISPENSER || evt
				.getView().getTopInventory().getType() == InventoryType.DROPPER))) {
			if (UnstorePossible.contains((Player) evt.getWhoClicked())) {
				if ((evt.getInventory().getType() == InventoryType.CHEST
						|| evt.getInventory().getType() == InventoryType.ENDER_CHEST
						|| evt.getInventory().getType() == InventoryType.HOPPER
						|| evt.getInventory().getType() == InventoryType.DISPENSER || evt
						.getInventory().getType() == InventoryType.DROPPER)) {
					int chestSize = evt.getView().getTopInventory().getSize();
					int num = 0;
					ItemStack m = new ItemStack(Material.AIR, 0);
					for (int i : evt.getNewItems().keySet()) {
						if (!(i < chestSize)) {
							ItemStack item = evt.getNewItems().get(i);
							if (num == 0) {
								m = item.clone();
							}
							if (evt.getView().getBottomInventory()
									.getItem(evt.getView().convertSlot(i)) != null) {
								num += item.getAmount()
										- evt.getView()
												.getBottomInventory()
												.getItem(
														evt.getView()
																.convertSlot(i))
												.getAmount();
							} else {
								num += item.getAmount();
							}
						}
					}
					if (m.getType() == Material.AIR) {
						return;
					}
					m.setAmount(num);
					UnstoreEvent event = new UnstoreEvent(
							(Player) evt.getWhoClicked(), m, evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
					}
				}
			}
		}

	}

	@EventHandler
	public void onUnstore(InventoryClickEvent evt) {
		if ((evt.getAction() == InventoryAction.PLACE_ONE
				|| evt.getAction() == InventoryAction.PLACE_SOME
				|| evt.getAction() == InventoryAction.PLACE_ALL
				|| evt.getAction() == InventoryAction.SWAP_WITH_CURSOR) && evt.getClickedInventory() != null) {
			if (evt.getClickedInventory().getType() == InventoryType.PLAYER) {
				if (evt.isLeftClick()
						&& UnstorePossible.contains((Player) evt
								.getWhoClicked())) {
					UnstoreEvent event = new UnstoreEvent(
							(Player) evt.getWhoClicked(), evt.getCursor(), evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
						return;
					}
					UnstorePossible.remove((Player) evt.getWhoClicked());
				}
				if (evt.isRightClick()
						&& UnstorePossible.contains((Player) evt
								.getWhoClicked())) {
					ItemStack i = evt.getCursor().clone();
					i.setAmount(1);
					UnstoreEvent event = new UnstoreEvent(
							(Player) evt.getWhoClicked(), i, evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
					}
				}
			}
		}
		if (evt.getAction() == InventoryAction.DROP_ALL_CURSOR
				|| evt.getAction() == InventoryAction.DROP_ALL_SLOT
				|| evt.getAction() == InventoryAction.DROP_ONE_CURSOR
				|| evt.getAction() == InventoryAction.DROP_ONE_SLOT) {
			if ((evt.getView().getTopInventory().getType() == InventoryType.CHEST
					|| evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
					|| evt.getView().getTopInventory().getType() == InventoryType.HOPPER
					|| evt.getView().getTopInventory().getType() == InventoryType.DISPENSER || evt
					.getView().getTopInventory().getType() == InventoryType.DROPPER)
					&& evt.getView().getBottomInventory().getType() == InventoryType.PLAYER) {
				if (evt.isLeftClick()
						&& UnstorePossible.contains((Player) evt
								.getWhoClicked())) {
					UnstoreEvent event = new UnstoreEvent(
							(Player) evt.getWhoClicked(), evt.getCursor(), evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
						return;
					}
					UnstorePossible.remove((Player) evt.getWhoClicked());
				}
				if (evt.isRightClick()
						&& UnstorePossible.contains((Player) evt
								.getWhoClicked())) {
					ItemStack i = evt.getCursor().clone();
					i.setAmount(1);
					UnstoreEvent event = new UnstoreEvent(
							(Player) evt.getWhoClicked(), i, evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
					}
				}
			}

		}
		if (evt.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
			if (evt.isShiftClick()
					&& (evt.getClickedInventory().getType() == InventoryType.CHEST
							|| evt.getClickedInventory().getType() == InventoryType.ENDER_CHEST
							|| evt.getClickedInventory().getType() == InventoryType.HOPPER
							|| evt.getClickedInventory().getType() == InventoryType.DISPENSER || evt
							.getClickedInventory().getType() == InventoryType.DROPPER)) {
				if ((evt.getView().getTopInventory().getType() == InventoryType.CHEST
						|| evt.getView().getTopInventory().getType() == InventoryType.ENDER_CHEST
						|| evt.getView().getTopInventory().getType() == InventoryType.HOPPER
						|| evt.getView().getTopInventory().getType() == InventoryType.DISPENSER || evt
						.getView().getTopInventory().getType() == InventoryType.DROPPER)
						&& evt.getView().getBottomInventory().getType() == InventoryType.PLAYER) {
					UnstoreEvent event = new UnstoreEvent(
							(Player) evt.getWhoClicked(), evt.getCurrentItem(), evt.getInventory());
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled()) {
						evt.setCancelled(true);
					}
				}
			}
		}
	}

}
