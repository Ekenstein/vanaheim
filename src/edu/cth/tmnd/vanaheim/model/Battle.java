package edu.cth.tmnd.vanaheim.model;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.StateHandler.State;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

/**
 * Battle handles a battle in the game.
 * Battle will be associated with the monster and
 * the player and will provide information about
 * the monster and the player.
 * 
 * @author Gabriel Ekblad
 *
 */
public final class Battle {

	private final Monster monster;
	private final Player player;
	private final Tile tile;
	
	/**
	 * Instantiates the Battle with a monster
	 * and the tile the battle happened to be generated
	 * on.
	 * @param m	the generated monster.
	 * @param t	the tile the battle was generated on.
	 */
	public Battle(Monster m, Tile t) {
		this.monster = m;
		
		// register the monster.
		m.register();
		
		// fetch the player from the object mapper.
		this.player = (Player) ObjectMapper.getInstance().getObject(Constants.PLAYER_OBJECT_NAME);
		this.tile = t;
	}
	
	/**
	 * Returns the monster's current HP.
	 * @return	the monster's current HP.
	 */
	public int getMonsterCurrentHP() {
		return this.monster.getCurrentHP();
	}
	
	/**
	 * Returns the monster's max HP.
	 * @return	the monster's max HP.
	 */
	public int getMonsterMaxHP() {
		return this.monster.getMaxHP();
	}
	
	/**
	 * Returns the monster's health percentage
	 * @return	the monster's health percentage.
	 */
	public int getMonsterHealthPercentage() {
		return (this.getMonsterCurrentHP() / this.getMonsterMaxHP()) * 100;
	}
	
	/**
	 * Returns the player's current HP.
	 * @return	the player's current HP.
	 */
	public int getPlayerCurrentHP() {
		return this.player.getCurrentHP();
	}
	
	/**
	 * Destructs the battle. That is, it will
	 * unregister the monster from the object mapper
	 * and will drop the monster's items on the tile
	 * the monster was generated on.
	 */
	public void destruct() {
		this.monster.unregister();
		
		// drop items on tile
		MessageBuffer mb = MessageBuffer.getInstance();
		for(Item i : this.monster.dropItems()) {
			this.tile.addItem(i);
			mb.append(this.monster.getName() + " dropped: " + i.getItemName());
		}
	}
	
	/**
	 * Returns the player's max HP.
	 * @return	the player's max HP.
	 */
	public int getPlayerMaxHP() {
		return this.player.getMaxHP();
	}
	
	/**
	 * Returns the player's health percentage.
	 * @return	the player's health percentage.
	 */
	public int getPlayerHealthPercentage() {
		return (this.getPlayerCurrentHP() / this.getPlayerMaxHP()) * 100;
	}
	
	/**
	 * The monster will hit the player with its damage.
	 */
	public void hitPlayer() {
		StateHandler.getInstance().push(State.FIGHTING, this.monster);
		this.player.damage(this.monster.getDamage());
	}

	/**
	 * Checks if the battle has ended.
	 * The battle has ended if either the player's current HP
	 * is equal to 0 or if the monster's HP is equal to 0
	 * @return	true if the battle has ended, otherwise false.
	 */
	public boolean hasEnded() {
		if(this.getPlayerCurrentHP() <= 0 || this.getMonsterCurrentHP() <= 0){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if the monster won the battle.
	 * That is, if the player's current hp is equal to 0
	 * @return	true if the monster won, otherwise false.
	 */
	public boolean monsterWin() {
		return this.getPlayerCurrentHP() <= 0;
	}
	
	/**
	 * Checks if the player won the battle.
	 * That is, if the monster's urrent HP is equal to 0.
	 * @return	true if the player won, otherwise false.
	 */
	public boolean playerWin() {
		return this.getMonsterCurrentHP() <= 0;
	}
	
	/**
	 * Returns the name of the monster.
	 * @return	monster's name.
	 */
	public String getMonsterName() {
		return this.monster.getName();
	}
}
