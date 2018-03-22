package main;

import java.util.ArrayList;

import com.emicb.engine.AbstractGame;
import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

public class GameManager extends AbstractGame {
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public GameManager() {
		
	}
	
	public void reset() {
		
	}

	@Override
	public void update(GameContainer gc, float dt) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, dt);
			if(objects.get(i).isDead()) {
				objects.remove(i);				//removes game object
				i--; 							//prevents skipping objects
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for(GameObject obj : objects) {
			obj.render(gc, r);
		}
	}
	
	//MAIN LOOP
	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.setWidth(854);
		gc.setHeight(480);
		gc.setScale(2f);
		
		gc.start();
	}

}
