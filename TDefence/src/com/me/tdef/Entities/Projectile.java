package com.me.tdef.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Niko
 *
 */
public class Projectile extends Entity {
	private static Texture spriteSheet;
	
	private int minDamage;
	private int maxDamage;
	private int area;
	
	/**
	 * Returns the area of effect of the projectile.
	 */
	public int getArea() {
		return area;
	}
	
	public int getMinDamage() {
		return minDamage;
	}
	
	public int getMaxDamage() {
		return maxDamage;
	}
	
	/**
	 * Load the sprite sheet for all projectiles.
	 */
	public static void loadSpriteSheet(String path) {
		spriteSheet = new Texture(Gdx.files.internal(path));
	}
	
	public static void disposeSpriteSheet() {
		spriteSheet.dispose();
	}
	
	public Projectile(int[] code, Vector2 position, float barrelRotation) {
		initialize();
		currentAnimation.update(0f);
		this.position = new Vector2(position.x, position.y - this.getOrigin().y);
		this.rotation = barrelRotation;
	}
	
	public void update(float deltaTime) {
		currentAnimation.update(deltaTime);
		this.applyVelocities();
	}
	
	private void initialize() {
			currentAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 12, 5, 0, 1, 0);
			tangentialVelocityMax = 12;
			minDamage = 4;
			maxDamage = 6;
			area = 1;
			scaleX = 1f;
			scaleY = 1f;
		
	}

}
