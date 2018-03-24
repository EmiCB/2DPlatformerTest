package main;

import java.awt.event.KeyEvent;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

public class Player extends GameObject{
	
	private int tileX, tileY;
	private float offX, offY;
	
	private float speed = 70;
	private float fallSpeed = 10;
	private float jump = 4;
	private float fallDistance = 0;
	
	private boolean grounded = false;

	public Player(int posX, int posY) {
		this.tag = "Player";
		this.tileX = posX;
		this.tileY = posY;
		this.offX = 0;
		this.offY = 0;
		this.positionX = posX * GameManager.TS;
		this.positionY = posY * GameManager.TS;
		this.width = GameManager.TS;
		this.height = GameManager.TS;
	}
	
	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		//Left & Right
		if(gc.getInput().isKey(KeyEvent.VK_D)) {
			if(gm.getCollision(tileX + 1, tileY) || gm.getCollision(tileX + 1, tileY + (int)Math.signum((int)offY))) {
				if(offX < 0) {
					offX += dt * speed;
					if(offX > 0) offX = 0;
				}
				else offX = 0;
			}
			else offX += dt * speed;
		}
		if(gc.getInput().isKey(KeyEvent.VK_A)) {
			if(gm.getCollision(tileX - 1, tileY) || gm.getCollision(tileX - 1, tileY + (int)Math.signum((int)offY))) {
				if(offX > 0) {
					offX -= dt * speed;
					if(offX < 0) offX = 0;
				}
				else offX = 0;
			}
			else offX -= dt * speed;
		}
		//End of Left & Right
		
		//Gravity & Jumping
		fallDistance += dt * fallSpeed;
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_W) && grounded) {
			fallDistance = -jump;
			grounded = false;
		}
		
		offY += fallDistance;
		
		if(fallDistance < 0) {
			if((gm.getCollision(tileX, tileY - 1) || gm.getCollision(tileX + (int)Math.signum((int)offX), tileY - 1)) && offY < 0) {
				fallDistance = 0;
				offY = 0;
			}
		}
		if(fallDistance > 0) {
			if((gm.getCollision(tileX, tileY + 1) || gm.getCollision(tileX + (int)Math.signum((int)offX), tileY + 1)) && offY > 0) {
				fallDistance = 0;
				offY = 0;
				grounded = true;
			}
		}
		//End of Jumping & Gravity
		
		//Final position
		if(offY > GameManager.TS / 2) {
			tileY++;
			offY -= GameManager.TS;
		}
		if(offY < -GameManager.TS / 2) {
			tileY--;
			offY += GameManager.TS;
		}
		if(offX > GameManager.TS / 2) {
			tileX++;
			offX -= GameManager.TS;
		}
		if(offX < -GameManager.TS / 2) {
			tileX--;
			offX += GameManager.TS;
		}
			
		positionX = tileX * GameManager.TS + offX;
		positionY = tileY * GameManager.TS + offY;
		//End of Final position
		
		//Projectile firing
		if(gc.getInput().isKeyDown(KeyEvent.VK_UP)) gm.addObject(new Projectile(tileX, tileY, offX + width / 2, offY + height / 2, 0));
		if(gc.getInput().isKeyDown(KeyEvent.VK_RIGHT)) gm.addObject(new Projectile(tileX, tileY, offX + width / 2, offY + height / 2, 1));
		if(gc.getInput().isKeyDown(KeyEvent.VK_DOWN)) gm.addObject(new Projectile(tileX, tileY, offX + width / 2, offY + height / 2, 2));
		if(gc.getInput().isKeyDown(KeyEvent.VK_LEFT)) gm.addObject(new Projectile(tileX, tileY, offX + width / 2, offY + height / 2, 3));
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawRectFill((int)positionX, (int)positionY, width, height, 0xff00ff00);		//placeholder player
	}

}
