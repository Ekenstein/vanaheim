package edu.cth.tmnd.vanaheim.model.creatures.player;

import java.util.List;
import java.util.Map;

import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class Player extends Human {

	public Player(final float x, final float y, final int velocity,
			final int maxHP, final String creatureName) throws SlickException {
		super(x, y, velocity, maxHP, creatureName, true);
		
		super.objectMapper.registerObject("inventory", super.inventory);
		super.inventory.addItem(new Axe());
		super.inventory.addItem(new HealthPotion());
		super.inventory.addItem(new Gold());
		ObjectMapper.getInstance().registerObject("quest log", super.questBook);
	}
	
	public Map<String, Quest> getQuests() {
		return super.questBook.getQuests();
	}
	
	public List<Item> getItems() {
		return super.inventory.getItems();
	}
	
	public boolean isInventoryToggled() {
		return super.inventory.isToggled();
	}
	
	public boolean isQuestBookToggled() {
		return super.questBook.isToggled();
	}

	@Override
	public void talk(Human human) {
		// TODO Auto-generated method stub

	}

}
