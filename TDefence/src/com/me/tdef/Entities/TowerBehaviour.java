package com.me.tdef.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TowerBehaviour {

	public void apply(Tower tower, Array<Enemy> enemies) {
		for(Enemy e : enemies) {
			if(e.getPosition().dst(tower.getPosition()) < tower.getAttackRange()) {
				tower.setFacingPoint(new Vector2(e.getPosition().x + e.getOrigin().x, e.getPosition().y + e.getOrigin().y));
				
				if(distance(tower.getRotation(), tower.getTargetRotation()) < 2)
					tower.shoot();
			}
		}
	}
	
	private float distance(float a, float b) {
		return a < b ? b - a : a - b;
	}
}
