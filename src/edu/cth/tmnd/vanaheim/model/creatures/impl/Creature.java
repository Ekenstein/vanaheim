package edu.cth.tmnd.vanaheim.model.creatures.impl;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.GameObject;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

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
			final String creatureName, boolean register) {
		super(x, y, creatureName, register);
		this.velocity = velocity;
		this.inventory = new Inventory(Constants.DEFAULT_INVENTORY_SPACE);
		this.maxHP = this.currentHP = maxHP;
	}

	public int getVelocity() {
		return this.velocity;
	}

	public void equip(final EquipableItem item) {
		if(this.equipment != null) {
			return;
		}

		this.equipment = item;
	}
	
	public Item getEquipedItem(){
		return this.equipment;
	}

	public void unequip() {
		this.equipment = this.inventory.addItem(this.equipment) ? null : this.equipment;
	}

	public void setMaxHP(final int hp) {
		this.maxHP = hp;
	}

	public void heal(final int hp) {
		final int healed = this.currentHP + hp;

		this.currentHP = healed > this.maxHP ? this.maxHP : healed;
	}

	public void damage(final int damage) {
		this.currentHP -= damage > this.currentHP ? this.currentHP : damage;
	}

	public boolean isDead() {
		return this.currentHP == 0;
	}

	public void setVelocity(final int velocity) {
		this.velocity = velocity;
	}

	public boolean addItem(final Item item) {
		return this.inventory.addItem(item);
	}

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
	
	public Inventory getInventory(){
		return inventory;
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
}
