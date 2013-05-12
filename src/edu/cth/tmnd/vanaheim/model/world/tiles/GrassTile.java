package edu.cth.tmnd.vanaheim.model.world.tiles;

import java.util.Random;
import edu.cth.tmnd.vanaheim.model.Battle;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;
import java.util.List;
import java.util.ArrayList;

public class GrassTile extends Tile {
	
	private Random rand;
	private final List<Monster> monsters;
	
	public GrassTile() {
		this.monsters = new ArrayList<Monster>();
		rand = new Random();
	}

	@Override
	public Battle hasMonster() { 
		if(this.rand.nextInt(4) == 1) {
			int monster = this.rand.nextInt(this.monsters.size());
			return new Battle(this.monsters.get(monster));
		}
		
		return null;
	}

	@Override
	protected boolean canContainItems() {
		return true;
	}
}
