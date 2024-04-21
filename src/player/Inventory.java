package player;

import gui.panels.CharactersPanel;
import gui.panels.DialogPanel;
import items.Item;
import items.armors.Armor;
import items.weapons.Weapon;
import util.FixedArrayList;
import util.interfaces.Interactive;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

/**
 * Inventory es una clase que representa el inventario del jugador.
 *
 * @version 1.0
 */
public class Inventory implements Serializable {

	/**
	 * La lista de elementos en el inventario.
	 */
	private final FixedArrayList<Item> items;

	/**
	 * Construye un nuevo inventario con una capacidad fija de 15.
	 */
	public Inventory() {

		items = new FixedArrayList<>(15);
	}

	/**
	 * Muestra el menú para equipar una armadura.
	 *
	 * @param player el jugador que equipará la armadura
	 */
	public void equipArmorMenu(Player player) {

		List<Armor> armors = items.filterArmors();
		String message = getArmorMessage(armors);
		try {

			int option = Integer.parseInt(JOptionPane.showInputDialog(message));
			if (option > 0 && option <= armors.size()) equipArmorAction(player, armors, option);
		} catch (Exception e) {
			Interactive.printDialog("Opción inválida.");
			equipArmorMenu(player);
		}
	}

	/**
	 * Obtiene el mensaje para equipar una armadura.
	 *
	 * @param armors la lista de armaduras
	 *
	 * @return el mensaje para equipar una armadura
	 */
	private String getArmorMessage(List<Armor> armors) {

		StringBuilder message = new StringBuilder("Equipar Armadura:\n");
		String itemMessage;
		Armor actualArmor;
		for (int i = 0; i < armors.size(); i++) {

			actualArmor = armors.get(i);
			itemMessage = String.format("%d. %s - %s\n", i + 1, actualArmor.getName(), actualArmor.getDescription());
			message.append(itemMessage);
		}
		message.append("0. Salir\n");
		return message.toString();
	}

	/**
	 * Realiza la acción de equipar una armadura.
	 *
	 * @param player el jugador que equipará la armadura
	 * @param armors la lista de armaduras
	 * @param option la opción seleccionada
	 */
	private void equipArmorAction(Player player, List<Armor> armors, int option) {

		Armor selectedArmor = armors.get(option - 1);
		switch (selectedArmor.getType()) {
			case HEAD -> {
				if (player.getHeadArmor() != null) items.add(player.getHeadArmor());
			}
			case CHEST -> {
				if (player.getChestArmor() != null) items.add(player.getChestArmor());
			}
			case LEGS -> {
				if (player.getLegArmor() != null) items.add(player.getLegArmor());
			}
			case HANDS -> {
				if (player.getHandArmor() != null) items.add(player.getHandArmor());
			}
			case FEET -> {
				if (player.getFootArmor() != null) items.add(player.getFootArmor());
			}
		}
		player.equipArmor(selectedArmor);
		player.printEquipArmor(selectedArmor);
		items.remove(selectedArmor);
	}

	/**
	 * Muestra el menú para equipar un arma.
	 *
	 * @param player el jugador que equipará el arma
	 */
	public void equipWeaponMenu(Player player) {

		List<Weapon> weapons = items.filterWeapons();
		String message = getWeaponMessage(weapons);
		try {
			int option = Integer.parseInt(JOptionPane.showInputDialog(message));
			if (option > 0 && option <= weapons.size()) {

				equipWeaponAction(player, weapons, option);
			}
		} catch (Exception e) {
			Interactive.printDialog("Opción inválida.");
			equipWeaponMenu(player);
		}
	}

	/**
	 * Realiza la acción de equipar un arma.
	 *
	 * @param player  el jugador que equipará el arma
	 * @param weapons la lista de armas
	 * @param option  la opción seleccionada
	 */
	private void equipWeaponAction(Player player, List<Weapon> weapons, int option) {

		Weapon selectedWeapon = weapons.get(option - 1);
		if (player.getWeapon() != null)
			items.add(player.getWeapon());
		player.equipWeapon(selectedWeapon);
		player.printEquipWeapon(selectedWeapon);
		items.remove(selectedWeapon);
	}

	/**
	 * Obtiene el mensaje para equipar un arma.
	 *
	 * @param weapons la lista de armas
	 *
	 * @return el mensaje para equipar un arma
	 */
	private String getWeaponMessage(List<Weapon> weapons) {

		StringBuilder message = new StringBuilder("Equipar Arma:\n");
		String itemMessage;
		Weapon actualWeapon;
		for (int i = 0; i < weapons.size(); i++) {

			actualWeapon = weapons.get(i);
			itemMessage = String.format("%d. %s - %s\n", i + 1, actualWeapon.getName(), actualWeapon.getDescription());
			message.append(itemMessage);
		}
		message.append("0. Salir\n");
		return message.toString();
	}

	/**
	 * Agrega un elemento al inventario.
	 *
	 * @param item el elemento a agregar
	 */
	public void addItem(Item item, DialogPanel panel) {

		String addMessage = String.format("%s se ha agregado al Inventario!", item.getName());
		panel.getText().append(items.add(item) ? addMessage : "Inventario Lleno.");
	}

	/**
	 * Remueve un elemento del inventario.
	 *
	 * @param item el elemento a remover
	 */
	public void removeItem(Item item) {

		items.remove(item);
	}

	/**
	 * Obtiene la lista de elementos en el inventario.
	 *
	 * @return la lista de elementos en el inventario
	 */
	public FixedArrayList<Item> getItems() {

		return items;
	}

	/**
	 * Imprime en pantalla los elementos en el inventario.
	 */
	public void printItems() {

		if (items.isEmpty()) {

			Interactive.printDialog("Inventario Vació.");
		} else {
			StringBuilder message = new StringBuilder("Inventario:\n");
			String itemMessage;
			for (Item item : items) {
				itemMessage = String.format("%s - %s\n", item.getName(), item.getDescription());
				message.append(itemMessage);
			}
			Interactive.printDialog(message.toString());
		}
	}
}
