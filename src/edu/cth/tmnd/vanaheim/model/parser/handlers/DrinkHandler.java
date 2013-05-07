package edu.cth.tmnd.vanaheim.model.parser.handlers;

import edu.cth.tmnd.vanaheim.model.items.impl.DrinkableItem;
import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;

public class DrinkHandler extends Handler {

	private final static int ITEM = 0;
	
	@Override
	protected boolean checkArgs(Object[] args) {
		if(args == null) {
			return false;
		}
		
		if(args.length != 1) {
			return false;
		}
		
		if(!(args[ITEM] instanceof DrinkableItem)) {
			return false;
		}
		
		return true;
	}

	@Override
	protected void handleArgs(Object[] args) {
		DrinkableItem item = (DrinkableItem) args[ITEM];
		
		item.drink(super.p);
	}

}
