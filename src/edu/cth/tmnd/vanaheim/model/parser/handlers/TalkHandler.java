package edu.cth.tmnd.vanaheim.model.parser.handlers;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.parser.handlers.impl.Handler;

public class TalkHandler extends Handler {

	@Override
	protected boolean checkArgs(Object[] args) {
		if(args == null) {
			return false;
		}
		
		if(args.length != 1) {
			return false;
		}
		
		if(!(args[0] instanceof NPC)) {
			return false;
		}
		
		return true;
	}

	@Override
	protected void handleArgs(Object[] args) {
		NPC p = (NPC) args[0];
		
		p.talk((Human)ObjectMapper.getInstance().getObject(Constants.PLAYER_OBJECT_NAME));
	}

}
