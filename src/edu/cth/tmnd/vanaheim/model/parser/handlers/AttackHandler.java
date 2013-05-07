package edu.cth.tmnd.vanaheim.model.parser.handlers;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.items.impl.WeaponItem;
import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;

final public class AttackHandler extends Handler {
	private final static int TARGET = 0;
	private final static int ITEM = 1;

	@Override
	protected void handleArgs(final Object[] args) {
		final WeaponItem item = (WeaponItem) args[ITEM];
		final Monster target = (Monster) args[TARGET];

		item.attack(super.p, target);
	}

	@Override
	protected boolean checkArgs(final Object[] args) {
		final int length = args.length;
		if(length <= 0 || length > 2) {
			return false;
		}

		if(!(args[TARGET] instanceof Monster)) {
			return false;
		}

		if(!(args[ITEM] instanceof WeaponItem)) {
			return false;
		}

		return true;
	}
}
