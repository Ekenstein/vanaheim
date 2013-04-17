package edu.cth.tmnd.vanaheim.model.creatures.player;

import java.awt.Point;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public class Player extends Human {

	public Player(float x, float y, Animation[] animation, float velocity,
			Inventory inventory, int maxHP) {
		super(x, y, animation, velocity, inventory, maxHP);
		// TODO Auto-generated constructor stub
	}

	QuestBook log;

	private float x = 34f, y = 34f;

	public void showQuestBook(){

	}
	
	public Point getLoc() {
		return new Point((int)x, (int)y);
	}
	
	public void setPlayerLoc(Point p) {
		x = (float)p.x;
		y = (float)p.y;
	}

	@Override
	public void talk(Human human) {
		// TODO Auto-generated method stub

	}

}
