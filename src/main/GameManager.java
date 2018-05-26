package main;

import java.util.ArrayList;

import com.emicb.engine.AbstractGame;
import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;
import com.emicb.engine.gfx.Image;

import objects.Platform;
import objects.Player;

public class GameManager extends AbstractGame {
	
	// Testing Options
	public static String level1 = "/sprites/testlevel.png";
	public static String level2 = "/sprites/testlevel2.png";
	
	public static String currentLevel = level2;
	public static boolean showHitboxes = true;
	private boolean addAABBPlatform = true;
	public static boolean showLightShow = false;
	public static boolean enablePositionReset = true;
	// end of testing options
	
	
	public static final int TS = 16;		//tile size

	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Camera camera;
	
	private boolean[] collision;
	private int levelW, levelH;

	public GameManager() {
		objects.add(new Player(2, 2));
		if (addAABBPlatform) objects.add(new Platform());

		loadLevel(currentLevel);
		
		camera = new Camera("player");
	}

	@Override
	public void init(GameContainer gc) {
		if(showLightShow) gc.getRenderer().setAmbientColor(0xff808080);	//-1 turns off
		else gc.getRenderer().setAmbientColor(-1);
	}

	@Override
	public void update(GameContainer gc, float dt) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, this, dt);
			if (objects.get(i).isDead()) {
				objects.remove(i); // removes game object
				i--; // prevents skipping objects
			}
		}
		Physics.update();
		
		camera.update(gc, this, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		camera.render(r);
		
		//draws map tiles
		for (int y = 0; y < levelH; y++) {
			for (int x = 0; x < levelW; x++) {
				if (collision[x + y * levelW])
					r.drawRectFill(x * TS, y * TS, TS, TS, 0xff0f0f0f);
				else
					r.drawRectFill(x * TS, y * TS, TS, TS, 0xfff9f9f9);
			}
		}
		//end
		
		for (GameObject obj : objects) {
			obj.render(gc, r);
		}
	}

	public void loadLevel(String path) {
		Image levelImage = new Image(path);

		levelW = levelImage.getW();
		levelH = levelImage.getH();
		
		//tile-based collision
		collision = new boolean[levelW * levelH];

		for (int y = 0; y < levelImage.getH(); y++) {
			for (int x = 0; x < levelImage.getW(); x++) {
				int index = x + y * levelImage.getW();

				if (levelImage.getP()[index] == 0xff000000)
					collision[index] = true;
				else
					collision[index] = false;
			}
		}
		//end
	}
	
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	public GameObject getObject(String tag) {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getTag().equals(tag)) return objects.get(i);
		}
		return null;
	}
	
	public boolean getCollision(int x, int y) {
		if (x < 0 || x >= levelW || y < 0 || y >= levelH)
			return true;
		return collision[x + y * levelW];
	}

	// MAIN LOOP
	public static void main(String args[]) {
		GameContainer gc = new GameContainer(new GameManager());

		gc.start();
	}

	public int getLevelW() {
		return levelW;
	}

	public void setLevelW(int levelW) {
		this.levelW = levelW;
	}

	public int getLevelH() {
		return levelH;
	}

	public void setLevelH(int levelH) {
		this.levelH = levelH;
	}

}
