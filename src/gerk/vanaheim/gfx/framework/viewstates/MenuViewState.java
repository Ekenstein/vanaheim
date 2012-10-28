package gerk.vanaheim.gfx.framework.viewstates;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import gerk.vanaheim.gfx.framework.impl.IHandleViewState;
import gerk.vanaheim.gfx.framework.impl.ViewState;

public class MenuViewState extends ViewState {

	public MenuViewState(IHandleViewState parent) {
		super(parent);
	}

	@Override
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // Clear The Screen And The Depth Buffer
		GL11.glLoadIdentity(); // Reset The View

		GL11.glTranslatef(-1.5f, 0.0f, -6.0f); // Move Left 1.5 Units And Into The Screen 6.0

		GL11.glBegin(GL11.GL_TRIANGLES); // Drawing Using Triangles
		GL11.glVertex3f(0.0f, 1.0f, 0.0f); // Top
		GL11.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
		GL11.glVertex3f(1.0f, -1.0f, 0.0f); // Bottom Right
		GL11.glEnd(); // Finished Drawing The Triangle
	}

	@Override
	public void handleKeyEvent(int keyEvent) {
		switch(keyEvent) {
		case Keyboard.KEY_ESCAPE:
			super.parent.prevState();
		}
	}

}
