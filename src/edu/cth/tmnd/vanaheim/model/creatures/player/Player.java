package edu.cth.tmnd.vanaheim.model.creatures.player;

import java.util.List;

import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class Player extends Human {

	public Player(final float x, final float y, final int velocity,
			final int maxHP, final String creatureName) throws SlickException {
		super(x, y, velocity, maxHP, 10, creatureName, true);
		
		super.objectMapper.registerObject("inventory", super.inventory);
		super.inventory.addItem(new Axe());
		super.inventory.addItem(new HealthPotion());
		super.inventory.addItem(new HealthPotion());
		ObjectMapper.getInstance().registerObject("quest log", super.questBook);
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
	
	public void clearInventory(){
		super.inventory.clear();
	}
	
	public void toggleInventory(){
		super.inventory.toggle();
	}
	
	public void toggleQuestBook(){
		super.questBook.toggle();
	}

	@Override
	public void talk(Human human) {}
	

}
