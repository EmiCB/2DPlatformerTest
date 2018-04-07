package main;

import java.util.ArrayList;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

import components.Component;

public abstract class GameObject {
	protected String tag;
	protected float positionX, positionY;
	protected int width, height;
	protected int paddingSides, paddingTop;
	protected boolean dead = false;			//remove object
	
	protected ArrayList<Component> components = new ArrayList<Component>();
	
	public abstract void update(GameContainer gc, GameManager gm, float dt);
	public abstract void render(GameContainer gc, Renderer r);
	
	public abstract void collision(GameObject other);
	
	public void updateComponents(GameContainer gc, GameManager gm, float dt) {
		for(Component c : components) {
			c.update(gc, gm, dt);
		}
	}
	public void renderComponents(GameContainer gc, Renderer r) {
		for(Component c : components) {
			c.render(gc, r);
		}
	}
	public void addComponent(Component c) {
		components.add(c);
	}
	public void removeComponent(String tag) {
		for(int i = 0; i < components.size(); i++) {
			if(components.get(i).getTag().equalsIgnoreCase(tag)) components.remove(i);
		}
	}
	public Component findComponent(String tag) {
		for(int i = 0; i < components.size(); i++) {
			if(components.get(i).getTag().equalsIgnoreCase(tag)) return components.get(i);
		}
		return null;
	}
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public float getPositionX() {
		return positionX;
	}
	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}
	public float getPositionY() {
		return positionY;
	}
	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public int getPaddingSides() {
		return paddingSides;
	}
	public void setPaddingSides(int paddingSides) {
		this.paddingSides = paddingSides;
	}
	public int getPaddingTop() {
		return paddingTop;
	}
	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}
}
