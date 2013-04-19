package edu.cth.tmnd.vanaheim.model.parser;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.UseableItem;
import edu.cth.tmnd.vanaheim.model.parser.impl.Handler;

final public class AttackHandler extends Handler {
	private final static int TARGET = 0;
	private final static int ITEM = 1;

	@Override
	protected void handleArgs(final Object[] args) {
		final UseableItem item = (UseableItem) args[ITEM];
		final Creature target = (Creature) args[TARGET];

		item.use(target);
	}

	@Override
	protected boolean checkArgs(final Object[] args) {
		final int length = args.length;
		if(length <= 0 || length > 2) {
			return false;
		}

		if(!(args[TARGET] instanceof Creature)) {
			return false;
		}

		if(!(args[ITEM] instanceof UseableItem)) {
			return false;
		}

		return true;
	}
}
