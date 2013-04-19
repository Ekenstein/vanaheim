package edu.cth.tmnd.vanaheim.model.creatures.player;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public class Player extends Human {

	public Player(final float x, final float y, final int velocity,
			final Inventory inventory, final int maxHP, final String creatureName) {
		super(x, y, velocity, inventory, maxHP, creatureName);
		// TODO Auto-generated constructor stub
	}

	QuestBook log;

	private final float x = 34f, y = 34f;

	public void showQuestBook(){

	}

	@Override
	public void talk(final Human human) {
		// TODO Auto-generated method stub

	}

}
