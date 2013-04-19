package edu.cth.tmnd.vanaheim.model.parser;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.UseableItem;
import edu.cth.tmnd.vanaheim.model.parser.impl.Handler;

public class UseHandler extends Handler {

	@Override
	protected int getArgumentSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void handleHelper(final Object[] os) {
		final UseableItem item = (UseableItem) os[0];
		if(os.length == 2) {
			final Creature target = (Creature) os[1];
			item.use(target);
		} else {
			item.use();
		}
	}

}
