package main;

import java.util.ArrayList;

import com.emicb.engine.AbstractGame;
import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;
import com.emicb.engine.gfx.Image;

public class GameManager extends AbstractGame {

	public static final int TS = 16;		//tile size

	private boolean[] collision;
	private int levelW, levelH;

	private ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public GameManager() {
		objects.add(new Player(2, 2));

		loadLevel("/sprites/testlevel.png");
	}

	@Override
	public void init(GameContainer gc) {
		gc.getRenderer().setAmbientColor(-1);
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
	}

	@Override
	public void render(GameContainer gc, Renderer r) {

		for (int y = 0; y < levelH; y++) {
			for (int x = 0; x < levelW; x++) {
				if (collision[x + y * levelW])
					r.drawRectFill(x * TS, y * TS, TS, TS, 0xff0f0f0f);
				else
					r.drawRectFill(x * TS, y * TS, TS, TS, 0xfff9f9f9);
			}
		}

		for (GameObject obj : objects) {
			obj.render(gc, r);
		}
	}

	public void loadLevel(String path) {
		Image levelImage = new Image(path);

		levelW = levelImage.getW();
		levelH = levelImage.getH();

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

}
