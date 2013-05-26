package edu.cth.tmnd.vanaheim.model.parser.handlers;

import java.util.List;

import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;
import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;

public final class LootHandler extends Handler {

	@Override
	protected boolean checkArgs(Object[] args) {
		if(args.length <= 0 && args.length > 1) {
			return false;
		}
		
		if(args.length == 1 && !(args[0] instanceof Item)) {
			return false;
		}
		
		return true;
	}

	@Override
	protected void handleArgs(Object[] args) {
		if(args.length == 0) {
			List<Item> tileItems = super.c.lootTileItems(super.p.getX(), super.p.getY());
			
			if(tileItems == null) {
				super.msgBuffer.append("No items to loot!");
				return;
			}
			
			for(Item i : tileItems) {
				System.out.println(i.getItemName());
				if(i instanceof QuestItem) {
					QuestItem qItem = (QuestItem) i;
					this.handleQuestItem(qItem);
				} else {
					this.addItem(i);
				}
			}
		} else {
			Item item = (Item) args[0];
			
			if(item instanceof QuestItem) {
				QuestItem qItem = (QuestItem) item;
				this.handleQuestItem(qItem);
			} else {
				this.addItem(item);
			}
		}
	}
	
	private void handleQuestItem(QuestItem item) {
		System.out.println("Handleing quest item");
		String questName = item.questName();
		
		if(super.p.hasQuest(questName)) {
			super.p.addQuestItem(questName, item);
		}
	}
	
	private void addItem(Item item) {
		if(!super.p.addItem(item)) {
			super.msgBuffer.append("Can't carry any more items!");
		} else {
			super.msgBuffer.append("Added " + item.getItemName() + " to my inventory!");
		}
	}

}
