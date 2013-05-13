package edu.cth.tmnd.vanaheim.model.parser.handlers;

import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;

public class UnequipHandler extends Handler {

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
		
		if(!super.p.isEquipped()) {
			super.msgBuffer.append("No item is equipped!");
		} else if(super.p.isEquipped(item)) {
			super.p.unequip();
			super.msgBuffer.append("Unequipped " + item.getItemName());
		} else {
			super.msgBuffer.append("The item isn't equipped!");
		}
	}

}
