package main;

import java.awt.event.KeyEvent;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

public class Player extends GameObject{
	
	private float speed = 50;

	public Player(int posX, int posY) {
		this.tag = "player";
		
		this.positionX = posX * 16;
		this.positionY = posY * 16;
		this.width = 16;
		this.height = 16;
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		
		//simple movement
		if(gc.getInput().isKey(KeyEvent.VK_W)) positionY -= dt * speed;		//up
		if(gc.getInput().isKey(KeyEvent.VK_S)) positionY += dt * speed;		//down
		if(gc.getInput().isKey(KeyEvent.VK_A)) positionX -= dt * speed;		//left
		if(gc.getInput().isKey(KeyEvent.VK_D)) positionX += dt * speed;		//right
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawRectFill((int)positionX, (int)positionY, width, height, 0xff00ff00);		//placeholder player
	}

}
