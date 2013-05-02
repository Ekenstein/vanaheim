package edu.cth.tmnd.vanaheim.view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

   public Main() {
      super("Vanaheim");
   }
   
   public void initStatesList(GameContainer container) {
      addState(new Menu());
      addState(new ExploreState());
      addState(new FightState());
   }
   
   public static void main(String[] argv) {
      try {
         AppGameContainer container = new AppGameContainer(new Main());
         container.setDisplayMode(1024,768,false);
         container.start();
      } catch (SlickException e) {
         e.printStackTrace();
      }
   }
}