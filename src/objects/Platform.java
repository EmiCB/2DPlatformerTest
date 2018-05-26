package objects;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

import components.AABBComponent;
import main.GameManager;
import main.GameObject;

public class Platform extends GameObject {
	
	private int color = (int) (Math.random() * Integer.MAX_VALUE);
	
	public Platform() {
		this.tag = "platform";
		this.width = 32;
		this.height = 16;
		this.paddingSides = 0;
		this.paddingTop = 0;
		
		if (GameManager.currentLevel == GameManager.level1) {
			this.positionX = 40;
			this.positionY = 40;
		}
		else if (GameManager.currentLevel == GameManager.level2) {
			this.positionX = 40;
			this.positionY = 300;
		}
		
		this.addComponent(new AABBComponent(this));
	}

	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		//Movement
		//int temp = 0;
		//temp += dt;
		//positionY += Math.cos(temp) * 2;
		//end of movement
		
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
