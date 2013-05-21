package edu.cth.tmnd.vanaheim.model.creatures.impl;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.GameObject;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

/**
 * Creature describes a creature game object
 * in the world. A creature can have a velocity,
 * an inventory, a hp, equipment, direction and base
 * damage.
 * 
 *
 */
public abstract class Creature extends GameObject {

	public static enum Direction {
		LEFT, RIGHT, UP, DOWN
	};
	private int velocity;
	protected Inventory inventory;
	private int currentHP, maxHP;
	private EquipableItem equipment;
	private Direction currentDirection;
	private int dmg;

	public Creature(final float x, final float y, final int velocity, final int maxHP, 
			final int dmg, final String creatureName, boolean register) {
		super(x, y, creatureName, register);
		this.velocity = velocity;
		this.inventory = new Inventory(Constants.DEFAULT_INVENTORY_SPACE);
		this.maxHP = this.currentHP = maxHP;
		this.currentDirection = Creature.Direction.UP;
		this.dmg = dmg;
	}

	/**
	 * Returns the velocity of the creature.
	 * That is, how fast the creature move.
	 * 
	 * @return the velocity of the creature.
	 */
	public int getVelocity() {
		return this.velocity;
	}

	/**
	 * Equips an item to the creature.
	 * The given item mustn't be null.
	 * If there are already an equipped item present,
	 * that item must first be unequipped in order to
	 * equip the new item.
	 * 
	 * The given item must also be present in the
	 * inventory in order to be equipped.
	 * @param item	the item to be equipped
	 * @return	true if the item was succesfully equipped,
	 * 			otherwise false.
	 */
	public boolean equip(final EquipableItem item) {
		if(item == null) {
			return false;
		}
		
		if(this.equipment != null) {
			return false;
		}

		if(this.retrieveItem(item) == null) {
			return false;
		}
		
		this.equipment = item;
		return true;
	}
	
	/**
	 * Returns the currently equipped item.
	 * @return	the equipped item or null
	 * 			if there are no equipped item.
	 */
	public Item getEquippedItem(){
		return this.equipment;
	}

	/**
	 * Unequips the currently equipped item.
	 * Will only unequip the currently equipped item
	 * if there are any slots left in the inventory.
	 * If there are free slots in the inventory, the item
	 * will be added to the inventory and the equipment will
	 * be removed.
	 */
	public void unequip() {
		this.equipment = this.inventory.addItem(this.equipment) ? null : this.equipment;
	}

	/**
	 * Sets the max HP of the creature.
	 * @param hp	the new max HP.
	 */
	public void setMaxHP(final int hp) {
		this.maxHP = hp;
	}

	/**
	 * Heals the creature with the given amount of
	 * HP. If the given amount of HP exceeds the max HP,
	 * the current HP will be set to max HP.
	 * @param hp
	 */
	public void heal(final int hp) {
		final int healed = this.currentHP + hp;

		this.currentHP = healed > this.maxHP ? this.maxHP : healed;
	}

	/**
	 * Will damage the creature with the given amount of damage.
	 * If the damage > current hp, the HP will be set to 0.
	 * @param damage	the amount of damage the create will take.
	 */
	public void damage(final int damage) {
		this.currentHP -= damage > this.currentHP ? this.currentHP : damage;
	}

	/**
	 * Checks if the creature is dead or not.
	 * @return	true if the creature is dead,
	 * 			otherwise false.
	 */
	public boolean isDead() {
		return this.currentHP == 0;
	}

	/**
	 * Sets the velocity of the creature.
	 * @param velocity	the new velocity to be set.
	 */
	public void setVelocity(final int velocity) {
		this.velocity = velocity;
	}

	/**
	 * Adds an item to the creature's inventory.
	 * @param item	the item to be added.
	 * @return	true if the item was succesfully added,
	 * 			otherwise false.
	 */
	public boolean addItem(final Item item) {
		return this.inventory.addItem(item);
	}

	/**
	 * Retreives an item from the creature's inventory.
	 * @param item
	 * @return
	 */
	public Item retrieveItem(final Item item) {
		return this.inventory.retrieveItem(item);
	}

	public Item getItem(final Item item) {
		return this.inventory.getItem(item);
	}
	
	public Item getItem(String item){
		return this.inventory.getItem(item);
	}

	public int getCurrentHP() {
		return this.currentHP;
	}
	
	public int getMaxHP() {
		return this.maxHP;
	}

	@Override
	public void setX(final float x) {
		if(super.x > x) {
			this.currentDirection = Direction.LEFT;
		} else if(super.x < x) {
			this.currentDirection = Direction.RIGHT;
		}
		super.x = x;
	}

	@Override
	public void setY(final float y) {
		if(super.y > y) {
			this.currentDirection = Direction.UP;
		} else if(super.y < y) {
			this.currentDirection = Direction.DOWN;
		}

		super.y = y;
	}

	public void setY(final float y, final boolean ignoreDirection) {
		if(ignoreDirection) {
			super.y = y;
		} else {
			this.setY(y);
		}
	}

	public void setX(final float x, final boolean ignoreDirection) {
		if(ignoreDirection) {
			super.x = x;
		} else {
			this.setX(x);
		}
	}

	public void setDirection(final Direction d) {
		this.currentDirection = d;
	}

	public Direction getDirection() {
		return this.currentDirection;
	}
	
	public boolean isEquipped(EquipableItem item) {
		if(this.equipment == null) {
			return false;
		}
		return this.equipment.equals(item);
	}
	
	public boolean isEquipped() {
		return this.equipment != null;
	}
	
	public int getInventorySlotsLeft() {
		return this.inventory.getSlotsLeft();
	}
	
	public int getInventorySlots() {
		return this.inventory.getSlots();
	}
	
	public int getDamage() {
		return this.dmg;
	}
}
