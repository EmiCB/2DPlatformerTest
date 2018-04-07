package main;

import java.util.ArrayList;

import components.AABBComponent;

public class Physics {
	private static ArrayList<AABBComponent> aabbList = new ArrayList<AABBComponent>();
	
	public static void addAABBComponent(AABBComponent aabb) {
		aabbList.add(aabb);
	}
	public static void update() {
		//Collisions
		for(int i = 0 ; i < aabbList.size(); i++) {
			for(int j = i + 1; j < aabbList.size(); j++) {
				AABBComponent c0 = aabbList.get(i);
				AABBComponent c1 = aabbList.get(j);
				
				if(Math.abs(c0.getCenterX() - c1.getCenterX()) < c0.getHalfWidth() + c1.getHalfWidth()) {
					if(Math.abs(c0.getCenterY() - c1.getCenterY()) < c0.getHalfHeight() + c1.getHalfHeight()) {
						c0.getParent().collision(c1.getParent());
						c1.getParent().collision(c0.getParent());
					}
				}
			}
		}
		//End of Collisions
		aabbList.clear();
	}
}
