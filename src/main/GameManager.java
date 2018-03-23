package main;

import java.util.ArrayList;

import com.emicb.engine.AbstractGame;
import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;
import com.emicb.engine.gfx.Image;

public class GameManager extends AbstractGame {
	
	private int[] collision;		//basically just tiles
	private int levelW, levelH;
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public GameManager() {
		objects.add(new Player(2,2));
		
		loadLevel("/sprites/testlevel.png");
	}
	
	@Override
	public void init(GameContainer gc) {
		gc.getRenderer().setAmbientColor(-1);
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
		
		for(int y = 0; y < levelH; y++) {
			for(int x = 0; x < levelW; x++) {
				if(collision[x + y * levelW] == 1) r.drawRectFill(x * 16, y * 16, 16, 16, 0xff0f0f0f);
				else r.drawRectFill(x * 16, y * 16, 16, 16, 0xfff9f9f9);
			}
		}
		
		for(GameObject obj : objects) {
			obj.render(gc, r);
		}
	}
	
	public void loadLevel(String path) {
		Image levelImage = new Image(path);
		
		levelW = levelImage.getW();
		levelH = levelImage.getH();
		
		collision = new int[levelW * levelH];
		
		for(int y = 0; y < levelImage.getH(); y++) {
			for(int x = 0; x < levelImage.getW(); x++) {
				int index = x + y * levelImage.getW();
				
				if(levelImage.getP()[index] == 0xff000000) collision[index] = 1;
				else collision[index] = 0;
			}
		}
	}
	
	//MAIN LOOP
	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new GameManager());
		
		gc.start();
	}

}
