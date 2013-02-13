package com.me.tdef.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * EnemyGuidePoints are points in the map of the game that assing enemies tha walk over it a new direction.
 * These are used to avoid the need for any AI.
 * @author Niko
 */
public class EnemyGuidePoint {
	private float guideRotation;
	private Vector2 position;
	
	/**
	 * Gets the rotation in degrees that the guidepoint directs enemies to.
	 */
	public float getGuideRotation() {
		return guideRotation;
	}
	
	/**
	 * @param position The initial position of the point.
	 * @param guideRotation The rotation in degrees that the guidepoint directs enemies to.
	 */
	public EnemyGuidePoint(Vector2 position, float guideRotation) {
		this.guideRotation = guideRotation;
		this.position = position;
	}
	
	/**
	 * @param entityRect
	 * @return Returns true if entityRect intercects with the position of the guidepoint.
	 */
	public boolean isInRange(Rectangle entityRect) {
		return entityRect.contains(new Rectangle(position.x, position.y, 2, 2));
	}
}
