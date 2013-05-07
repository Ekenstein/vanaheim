package edu.cth.tmnd.vanaheim.model;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;



/**A class to represent a battle between the player and a monster
 * @author Daniel
 *
 */
public class Battle {

	Monster m;
	Player p;
	
	public Battle(Player p, Monster m) {
		this.m = m;
		this.p = p;
	}
	
	/**Check if the battle has ended
	 * @return True if the battle has ended else false
	 */
	public boolean hasEnded(){
		return false;
		
	}
}
