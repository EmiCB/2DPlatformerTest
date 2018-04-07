package components;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

import main.GameManager;

public abstract class Component {
	protected String tag;
	
	public abstract void update(GameContainer gc, GameManager gm, float dt);
	public abstract void render(GameContainer gc, Renderer r);
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
