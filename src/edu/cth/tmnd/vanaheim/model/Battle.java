package edu.cth.tmnd.vanaheim.model;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.StateHandler.State;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public final class Battle {

	private final Monster monster;
	private final Player player;
	private final Tile tile;
	
	public Battle(Monster m, Tile t) {
		this.monster = m;
		m.register();
		this.player = (Player) ObjectMapper.getInstance().getObject(Constants.PLAYER_OBJECT_NAME);
		this.tile = t;
	}
	
	public int getMonsterCurrentHP() {
		return this.monster.getCurrentHP();
	}
	
	public int getMonsterMaxHP() {
		return this.monster.getMaxHP();
	}
	
	public int getMonsterHealthPercentage() {
		return (this.getMonsterCurrentHP() / this.getMonsterMaxHP()) * 100;
	}
	
	public int getPlayerCurrentHP() {
		return this.player.getCurrentHP();
	}
	
	public void destruct() {
		this.monster.unregister();
		
		// drop items on tile
		MessageBuffer mb = MessageBuffer.getInstance();
		for(Item i : this.monster.dropItems()) {
			this.tile.addItem(i);
			mb.append(this.monster.getName() + " dropped: " + i.getItemName());
		}
	}
	
	public int getPlayerMaxHP() {
		return this.player.getMaxHP();
	}
	
	public int getPlayerHealthPercentage() {
		return (this.getPlayerCurrentHP() / this.getPlayerMaxHP()) * 100;
	}
	
	public void hitPlayer() {
		StateHandler.getInstance().push(State.FIGHTING, this.monster);
		this.player.damage(this.monster.getDamage());
	}

	public boolean hasEnded() {
		if(this.getPlayerCurrentHP() <= 0 || this.getMonsterCurrentHP() <= 0){
			return true;
		}
		
		return false;
	}
	
	public boolean monsterWin() {
		return this.getPlayerCurrentHP() <= 0;
	}
	
	public boolean playerWin() {
		return this.getMonsterCurrentHP() <= 0;
	}
	
	public String getMonsterName() {
		return this.monster.getName();
	}
}
