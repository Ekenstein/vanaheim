package edu.cth.tmnd.vanaheim.model.creatures.impl;

import edu.cth.tmnd.vanaheim.model.GameObject;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public abstract class Creature extends GameObject {

	public static enum Direction {
		LEFT, RIGHT, UP, DOWN
	};
	protected int velocity;
	protected Inventory inventory;
	protected int currentHP, maxHP;
	protected EquipableItem equipment;
	protected Direction currentDirection;


	public Creature(final float x, final float y, final int velocity, final Inventory inventory, final int maxHP) {
		super(x, y);
		this.velocity = velocity;
		this.inventory = inventory;
		this.maxHP = this.currentHP = maxHP;
	}

	public int getVelocity() {
		return this.velocity;
	}

	public void equip(final EquipableItem item) {
		if(this.equipment != null) {
			return;
		}

		this.equipment = (EquipableItem) this.inventory.retrieveItem(item);
	}

	public void unequip() {
		this.equipment = this.inventory.addItem(this.equipment) ? null : this.equipment;
	}

	public void setMaxHP(final int hp) {
		this.maxHP = hp;
	}

	public int heal(final int hp) {
		final int healed = this.currentHP + hp;

		this.currentHP = healed > this.maxHP ? this.maxHP : healed;
		return healed - this.currentHP;
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

	public boolean destroyItem(final Item item) {
		return this.inventory.destroyItem(item);
	}

	public Item removeItem(final Item item) {
		return this.inventory.removeItem(item);
	}

	public Item getItem(final Item item) {
		return this.inventory.getItem(item);
	}

	public int getHP() {
		return this.currentHP;
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

	public Direction getDirection() {
		return this.currentDirection;
	}
}
