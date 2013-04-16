package edu.cth.tmnd.vanaheim.model.creatures.player;

import java.awt.Point;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public class Player implements Human {

	QuestBook log;

	private Animation sprite, up, down, left, right;

	private float x = 34f, y = 34f;

	public Player(){

	}

	public void showQuestBook(){

	}
	
	public Point getLoc() {
		return new Point((int)x, (int)y);
	}

	@Override
	public int getCurrentHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getExp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, Graphics context)
			throws SlickException {
		sprite.draw((int)x, (int)y);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {	
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_UP))
		{
			sprite = up;
			sprite.update(delta);
			// The lower the delta the slowest the sprite will animate.
			y -= delta * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			sprite = down;
			sprite.update(delta);
			y += delta * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = left;
			sprite.update(delta);
			x -= delta * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = right;
			sprite.update(delta);
			x += delta * 0.1f;
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		Image [] movementUp = {new Image("data/wiz1.png"), new Image("data/wiz2.png")};
		Image [] movementDown = {new Image("data/wiz1.png"), new Image("data/wiz2.png")};
		Image [] movementLeft = {new Image("data/wiz1.png"), new Image("data/wiz2.png")};
		Image [] movementRight = {new Image("data/wiz1.png"), new Image("data/wiz2.png")};
		int [] duration = {300, 300};

		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false);

		sprite = right;
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub
	}

	@Override
	public void talk(Human human) {
		// TODO Auto-generated method stub

	}

}
