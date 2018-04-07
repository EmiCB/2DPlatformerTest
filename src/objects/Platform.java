package objects;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

import components.AABBComponent;
import main.GameManager;
import main.GameObject;

public class Platform extends GameObject {
	
	private int color = (int) (Math.random() * Integer.MAX_VALUE);
	
	public Platform() {
		this.tag = "platfrom";
		this.width = 32;
		this.height = 16;
		this.paddingSides = 0;
		this.paddingTop = 0;
		
		//temp
		this.positionX = 18;
		this.positionY = 11;
		//
		
		this.addComponent(new AABBComponent(this));
	}

	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		this.updateComponents(gc, gm, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawRectFill((int)positionX, (int)positionY, width, height, color);
		this.renderComponents(gc, r);
	}

	@Override
	public void collision(GameObject other) {
		color = (int) (Math.random() * Integer.MAX_VALUE);
	}
	
}