package main;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

public class Camera {
	private float offX, offY;
	
	private String targetTag;
	private GameObject target = null;
	
	//private int camSpeed = 20;
	
	public Camera(String tag) {
		this.targetTag = tag;
	}
	
	public void update(GameContainer gc, GameManager gm, float dt) {
		//sets camera directly
		if(target == null) target = gm.getObject(targetTag);
		if(target == null) return;
		
		//move faster the further away it is
		float targetX = (target.getPositionX() + target.getWidth() / 2) - gc.getWidth() / 2;
		float targetY = (target.getPositionY() + target.getHeight() / 2) - gc.getHeight() / 2;
		
		offX = targetX;
		offY = targetY;
		//offX -= dt * (int)(offX - targetX) * camSpeed;
		//offY -= dt * (int)(offY - targetY) * camSpeed;
		
		//keep camera from leaving level
		if(offX < 0) offX = 0;
		if(offY < 0) offY = 0;
		if(offX + gc.getWidth() > gm.getLevelW() * GameManager.TS) offX = gm.getLevelW() * GameManager.TS - gc.getWidth();
		if(offY + gc.getHeight() > gm.getLevelH() * GameManager.TS) offY = gm.getLevelH() * GameManager.TS - gc.getHeight();
	}
	
	public void render(Renderer r) {
		r.setCamX((int)offX);
		r.setCamY((int)offY);
	}

	
	
	public float getOffX() {
		return offX;
	}

	public void setOffX(float offX) {
		this.offX = offX;
	}

	public float getOffY() {
		return offY;
	}

	public void setOffY(float offY) {
		this.offY = offY;
	}

	public String getTargetTag() {
		return targetTag;
	}

	public void setTargetTag(String targetTag) {
		this.targetTag = targetTag;
	}

	public GameObject getTarget() {
		return target;
	}

	public void setTarget(GameObject target) {
		this.target = target;
	}
}
