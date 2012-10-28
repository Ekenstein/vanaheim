package gerk.vanaheim.gfx.framework;

import gerk.vanaheim.gfx.framework.impl.IHandleViewState;
import gerk.vanaheim.gfx.framework.impl.ViewState;
import gerk.vanaheim.gfx.framework.viewstates.ExitViewState;
import gerk.vanaheim.gfx.framework.viewstates.MenuViewState;
import gerk.vanaheim.misc.ViewStateList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 * Container for the initiating states of the game.
 * 
 * @author Gabriel Ekblad
 */
public class DisplayContainer implements IHandleViewState {
	private static DisplayContainer theInstance = null;
	
	public static DisplayContainer getInstance() {
		if(theInstance == null) {
			theInstance = new DisplayContainer();
		}
		
		return theInstance;
	}
	
	private ViewStateList viewStates = new ViewStateList();
	
	private DisplayContainer() {
		this.viewStates.add(new ExitViewState(this));
		this.viewStates.add(new MenuViewState(this));
	}
	
	private void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setVSyncEnabled(true);
			Display.setTitle("example");
			Display.create();
		} catch(LWJGLException e) {
			Sys.alert("Error", "Initialization failed!\n\n" + e.getMessage());
			System.exit(1);
		}
	}
	
	private void initGL() {
		int width = Display.getDisplayMode().getWidth();
		int height = Display.getDisplayMode().getHeight();

		GL11.glViewport(0, 0, width, height); // Reset The Current Viewport
		GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
		GL11.glLoadIdentity(); // Reset The Projection Matrix
		GLU.gluPerspective(45.0f, ((float) width / (float) height), 0.1f, 100.0f); // Calculate The Aspect Ratio Of The Window
		GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix
		GL11.glLoadIdentity(); // Reset The Modelview Matrix

		GL11.glShadeModel(GL11.GL_SMOOTH); // Enables Smooth Shading
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		GL11.glClearDepth(1.0f); // Depth Buffer Setup
		GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
		GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Test To Do
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST); // Really Nice Perspective Calculations
	}
	
	public void run() {
		this.createDisplay();
		this.initGL();
		
		while(!Display.isCloseRequested()) {
			ViewState s = this.viewStates.get();
			s.render();
			s.pollInput();
			Display.update();
		}
	}

	private void exit() {
		Display.destroy();
		System.exit(0);
	}
	
	@Override
	public void setState(ViewState s) {
	}

	@Override
	public void nextState() {
	}

	@Override
	public void prevState() {
		if(this.viewStates.isRoot()) {
			this.exit();
		}
		
		this.viewStates.prev();
	}
}
