package edu.cth.tmnd.vanaheim.model.parser.handlers;

import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;
import edu.cth.tmnd.vanaheim.model.Container;

public final class CloseHandler extends Handler {

	@Override
	protected boolean checkArgs(Object[] args) {
		if(args.length != 1) {
			return false;
		}
		
		if(!(args[0] instanceof Container)) {
			return false;
		}
		
		return true;
	}

	@Override
	protected void handleArgs(Object[] args) {
		Container c = (Container)args[0];
		c.setToggle(false);
	}

}
