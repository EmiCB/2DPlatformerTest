package objects;

import java.awt.event.KeyEvent;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;
import com.emicb.engine.gfx.ImageTile;

import components.AABBComponent;
import main.GameManager;
import main.GameObject;

public class Player extends GameObject{
	
	private ImageTile playerImage = new ImageTile("/sprites/playertest.png", GameManager.TS, GameManager.TS);
	
	private int direction = 0;
	private float animation;
	private float animationSpeed = 7;
	
	private int tileX, tileY;
	private float offX, offY;
	
	private float speed = 70;
	private float fallSpeed = 10;
	private float jump = 4;
	private float fallDistance = 0;
	
	private boolean grounded = false;

	public Player(int posX, int posY) {
		this.tag = "player";
		this.tileX = posX;
		this.tileY = posY;
		this.offX = 0;
		this.offY = 0;
		this.positionX = posX * GameManager.TS;
		this.positionY = posY * GameManager.TS;
		this.width = GameManager.TS;
		this.height = GameManager.TS;
		this.paddingSides = 3;
		this.paddingTop = 1;
		
		this.addComponent(new AABBComponent(this));
	}
	
	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		//Left & Right
		if(gc.getInput().isKey(KeyEvent.VK_D)) {
			if(gm.getCollision(tileX + 1, tileY) || gm.getCollision(tileX + 1, tileY + (int)Math.signum((int)offY))) {
				offX += dt * speed;
				if(offX > paddingSides) offX = paddingSides;
			}
			else offX += dt * speed;
		}
		if(gc.getInput().isKey(KeyEvent.VK_A)) {
			if(gm.getCollision(tileX - 1, tileY) || gm.getCollision(tileX - 1, tileY + (int)Math.signum((int)offY))) {
				offX -= dt * speed;
				if(offX < -paddingSides) offX = -paddingSides;
			}
			else offX -= dt * speed;
		}
		//End of Left & Right
		
		//Gravity & Jumping
		fallDistance += dt * fallSpeed;
		
		if((int)fallDistance != 0) grounded = false;
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_W) && grounded) {
			fallDistance = -jump;
			grounded = false;
		}
		
		offY += fallDistance;
		
		if(fallDistance < 0) {
			if((gm.getCollision(tileX, tileY - 1) || gm.getCollision(tileX + (int)Math.signum((int) Math.abs(offX) > paddingSides ? offX : 0), tileY - 1)) && offY < -paddingTop) {
				fallDistance = 0;
				offY = -paddingTop;
			}
		}
		if(fallDistance > 0) {
			if((gm.getCollision(tileX, tileY + 1) || gm.getCollision(tileX + (int)Math.signum((int)Math.abs(offX) > paddingSides ? offX : 0), tileY + 1)) && offY > 0) {
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
		
		//Animation
		if((int)fallDistance < 0) animation = 2;
		else if((int)fallDistance > 0) animation = 3;
		else if(gc.getInput().isKey(KeyEvent.VK_D) && (int)fallDistance == 0) {
			direction = 0;
			animation += dt * animationSpeed;
			if(animation >= 2) animation = 0;
		}
		else if(gc.getInput().isKey(KeyEvent.VK_A) && (int)fallDistance == 0) {
			direction = 1;
			animation += dt * animationSpeed;
			if(animation >= 2) animation = 0;
		}
		else animation = 0;
		//End of Animation
		
		this.updateComponents(gc, gm, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImageTile(playerImage, (int)positionX, (int)positionY, (int)animation, direction);
		//r.drawRectFill((int)positionX, (int)positionY, width, height, 0xff00ff00);		//placeholder player
		
		this.renderComponents(gc, r);
	}

	@Override
	public void collision(GameObject other) {
		// TODO Auto-generated method stub
		
	}

}