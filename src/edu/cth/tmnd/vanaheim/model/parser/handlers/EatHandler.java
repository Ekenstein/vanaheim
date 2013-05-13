package edu.cth.tmnd.vanaheim.model.parser.handlers;

import edu.cth.tmnd.vanaheim.model.items.impl.DrinkableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.EatableItem;
import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;

final public class EatHandler extends Handler {

	private final static int ITEM = 0;
	
	@Override
	protected boolean checkArgs(final Object[] args) {
		if(args == null) {
			return false;
		}
		
		if(args.length != 1) {
			return false;
		}
		
		if(!(args[ITEM] instanceof EatableItem)) {
			return false;
		}
		
		return true;
	}

	@Override
	protected void handleArgs(final Object[] args) {
		EatableItem item = (EatableItem) args[ITEM];
		
		item.eat(super.p);

	}
}
