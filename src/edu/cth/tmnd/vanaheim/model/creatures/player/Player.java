package edu.cth.tmnd.vanaheim.model.creatures.player;

import java.util.List;
import java.util.Map;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.ExploreQuest;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;
import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public class Player extends Human {
	
	private Inventory inventory;
	
	private QuestBook log;

	public Player(final float x, final float y, final int velocity,
			final Inventory inventory, final int maxHP, final String creatureName) {
		super(x, y, velocity, inventory, maxHP, creatureName);
		this.inventory = inventory;
		
		//For testing purpose
		inventory.addItem(new Axe(this));
		inventory.addItem(new HealthPotion(this));
		inventory.addItem(new Gold(this));
		
		log = new QuestBook();
		
		//For testing purpose
		log.addQuest(new ExploreQuest(this));
	}
	
	public Map<String, Quest> getQuests() {
		return this.log.getQuests();
	}
	
	public List<Item> getItems() {
		return this.inventory.getItems();
	}
	
	public boolean isInventoryToggled() {
		return this.inventory.isToggled();
	}
	
	public boolean isQuestBookToggled() {
		return this.log.isToggled();
	}

	@Override
	public void talk(Human human, String talk) {
		// TODO Auto-generated method stub

	}

}
