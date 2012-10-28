package gerk.vanaheim.gfx.framework.viewstates;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import gerk.vanaheim.gfx.framework.impl.IHandleViewState;
import gerk.vanaheim.gfx.framework.impl.ViewState;

public final class ExitViewState extends ViewState {

	public ExitViewState(IHandleViewState parent) {
		super(parent);
	}

	@Override
	public void render() {
		GL11.glBegin(GL11.GL_QUADS); // Draw A Quad
		GL11.glVertex3f(-1.0f, 1.0f, 0.0f); // Top Left
		GL11.glVertex3f(1.0f, 1.0f, 0.0f); // Top Right
		GL11.glVertex3f(1.0f, -1.0f, 0.0f); // Bottom Right
		GL11.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
		GL11.glEnd(); // Done Drawing The Quad
	}

	@Override
	public void handleKeyEvent(int keyEvent) {
		switch(keyEvent) {
		case Keyboard.KEY_ESCAPE:
			Display.destroy();
			System.exit(0);
		}
	}

}
