package edu.cth.tmnd.vanaheim.model.parser.handlers;

import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;

public final class EquipHandler extends Handler {

	@Override
	protected boolean checkArgs(Object[] args) {
		if(args.length != 1) {
			return false;
		}
		
		if(!(args[0] instanceof EquipableItem)) {
			return false;
		}
		
		return true;
	}

	@Override
	protected void handleArgs(Object[] args) {
		EquipableItem item = (EquipableItem) args[0];
		
		if(super.p.isEquipped()) {
			super.msgBuffer.append("Has already equipped an item!");
		} else {
			if(super.p.equip(item)) {
				super.msgBuffer.append("Equipped " + item.getItemName() + "!");
			} else {
				super.msgBuffer.append("No such item in the inventory!");
			}
		}
	}
}
