package edu.cth.tmnd.vanaheim.model.parser;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.UseableItem;
import edu.cth.tmnd.vanaheim.model.parser.impl.Handler;

final public class UseHandler extends Handler {

	private final static int ITEM = 0;
	private final static int TARGET = 1;

	@Override
	protected void handleArgs(final Object[] args) {
		final UseableItem item = (UseableItem) args[ITEM];

		if(args.length > 1) {
			final Creature target = (Creature) args[TARGET];
			item.use(target);
		} else {
			item.use();
		}
	}

	@Override
	protected boolean checkArgs(final Object[] args) {
		if(args.length <= 0) {
			return false;
		}

		if(!(args[ITEM] instanceof UseableItem)) {
			return false;
		}

		if(args.length > 1) {
			if(!(args[TARGET] instanceof Creature)) {
				return false;
			}
		}

		return true;
	}
}
