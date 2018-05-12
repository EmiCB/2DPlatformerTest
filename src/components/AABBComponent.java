package components;

import com.emicb.engine.GameContainer;
import com.emicb.engine.Renderer;

import main.GameManager;
import main.GameObject;
import main.Physics;

//AABB = Axis Aligned Bounding Box
public class AABBComponent extends Component {
	private GameObject parent;
	private int centerX, centerY;
	private int halfWidth, halfHeight;
	
	public AABBComponent(GameObject parent) {
		this.parent = parent;
		this.tag = "aabb";
	}
	
	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		centerX = (int) (parent.getPositionX() + (parent.getWidth() / 2));
		centerY = (int) (parent.getPositionY() + (parent.getHeight() / 2) + (parent.getPaddingTop() / 2));
		halfWidth = (parent.getWidth()) / 2 - parent.getPaddingSides();
		halfHeight = (parent.getHeight()) / 2 - (parent.getPaddingTop() / 2);
		
		Physics.addAABBComponent(this);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		if (GameManager.showHitboxes == true) {
			r.drawRect(centerX - halfWidth, centerY - halfHeight, halfWidth * 2, halfHeight * 2, 0xff000000);
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getHalfWidth() {
		return halfWidth;
	}

	public void setHalfWidth(int halfWidth) {
		this.halfWidth = halfWidth;
	}

	public int getHalfHeight() {
		return halfHeight;
	}

	public void setHalfHeight(int halfHeight) {
		this.halfHeight = halfHeight;
	}

	public GameObject getParent() {
		return parent;
	}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}
}
