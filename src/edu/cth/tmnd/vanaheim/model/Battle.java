package edu.cth.tmnd.vanaheim.model;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.StateHandler.State;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import java.util.List;

public final class Battle {

	private final Monster monster;
	private final Player player;
	
	public Battle(Monster m) {
		this.monster = m;
		this.player = (Player) ObjectMapper.getInstance().getObject(Constants.PLAYER_OBJECT_NAME);
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
	
	public int getPlayerMaxHP() {
		return this.player.getMaxHP();
	}
	
	public int getPlayerHealthPercentage() {
		return (this.getPlayerCurrentHP() / this.getPlayerMaxHP()) * 100;
	}
	
	public void hitPlayer() {
		// TODO Gör om så att gui bara får koordinater
		StateHandler.getInstance().push(State.FIGHTING, this.monster);
	}

	public boolean hasEnded() {
		return this.getPlayerCurrentHP() <= 0 || this.getMonsterCurrentHP() <= 0;
	}
	
	public List<Item> dropItems() {
		if(this.getMonsterCurrentHP() <= 0) {
			return this.monster.dropItems();
		}
		
		return null;
	}
}
