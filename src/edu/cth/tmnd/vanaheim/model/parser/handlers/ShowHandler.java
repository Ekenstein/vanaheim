package edu.cth.tmnd.vanaheim.model.parser.handlers;

import edu.cth.tmnd.vanaheim.model.Container;
import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;

public final class ShowHandler extends Handler {

	@Override
	protected boolean checkArgs(Object[] args) {
		if(args == null) {
			return false;
		}
		
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
		Container c = (Container) args[0];
		c.toggle();
	}

}
