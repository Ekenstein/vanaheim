package edu.cth.tmnd.vanaheim.view;

import java.awt.Font;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LoadingState extends BasicGameState {
	
	public static final int ID = 4;
	
	private StateBasedGame game;
	
	private DeferredResource nextResource;
	
	private TrueTypeFont font;

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		
		LoadingList.setDeferredLoading(true);
		
		font = new TrueTypeFont(new Font("Arial", Font.PLAIN, 18), false);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics context)
			throws SlickException {
		context.setColor(Color.white);
		if (nextResource != null) {
			String str = "Loading: " + nextResource.getDescription();
			int xPos = (1024 - font.getWidth(str)) / 2;
			context.drawString(str, xPos, 200);
		} else {
			String str = "Load complete!";
			int xPos = (1024 - font.getWidth(str)) / 2;
			context.drawString(str, xPos, 200);
		}
		
		int barWidth = 512;
		int total = LoadingList.get().getTotalResources();
		int barInterval = barWidth/total;
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
		
		if (total-loaded != 0) {
			context.setColor(Color.red);
		} else {
			context.setColor(Color.green);
		}
		context.fillRect(256,250,loaded*barInterval,20);
		context.drawRect(256,250,total*barInterval,20);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if (nextResource != null) {
			try {
				nextResource.load();
				// slow down loading for example purposes
				try { Thread.sleep(50); } catch (Exception e) {}
			} catch (IOException e) {
				throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
			}
			
			nextResource = null;
		} 
		
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
