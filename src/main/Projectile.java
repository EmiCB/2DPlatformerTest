package main;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

public class Projectile extends GameObject {
	
	private int tileX, tileY;
	private float offX, offY;
	
	private int direction;
	private float speed = 200;
	private int size = 4;

	public Projectile(int tileX, int tileY,float offX, float offY, int direction) {
		this.direction = direction;
		this.tileX = tileX;
		this.tileY = tileY;
		this.offX = offX;
		this.offY = offY;
		positionX = tileX * GameManager.TS + offX;
		positionY = tileY * GameManager.TS + offY;
	}
	
	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		
		switch(direction) {
			case 0: offY -= speed * dt;	break;	//up
			case 1: offX += speed * dt;	break;	//right
			case 2: offY += speed * dt;	break;	//down
			case 3: offX -= speed * dt;	break;	//left
		}
		
		//Final position
		if(offY > GameManager.TS / 2) {
			tileY++;
			offY -= GameManager.TS;
		}
		if(offY < 0) {
			tileY--;
			offY += GameManager.TS;
		}
		if(offX > GameManager.TS / 2) {
			tileX++;
			offX -= GameManager.TS;
		}
		if(offX < 0) {
			tileX--;
			offX += GameManager.TS;
		}
		
		if(gm.getCollision(tileX, tileY)) this.dead = true;
		
		positionX = tileX * GameManager.TS + offX;
		positionY = tileY * GameManager.TS + offY;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawRectFill((int)(positionX - (size / 2)), (int)(positionY - (size / 2)), size, size, 0xff0000ff);
	}
	
}
