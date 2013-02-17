package com.me.tdef.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.tdef.Constants;

/**
 * @author Niko
 *
 */
public class Projectile extends Entity {
	private static Texture spriteSheet;
	
	private int speed;
	private int minDamage;
	private int maxDamage;
	private int area;
	
	
	
	private Constants.ProjectileType projectileType;
	
	
	/**
	 * Returns the initial and maximun speed of the projectile.
	 */
	public int getSpeed() {
		return speed;
	}
	
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
	public static void loadSpriteSheet(Texture sheet) {
		spriteSheet = sheet;
	}
	
	public static void disposeSpriteSheet() {
		spriteSheet.dispose();
	}
	
	public Projectile(Constants.ProjectileType projectileType, Vector2 position, float barrelRotation) {
		this.projectileType = projectileType;
		initialize();
		this.position = position;
		this.rotation = barrelRotation;
		
		float vX = (float)Math.cos(barrelRotation) * speed;
		float vY = (float)Math.sin(barrelRotation) * speed;
		
		this.velocity = new Vector2(vX, vY);		
		
	}
	
	public void update(float deltaTime) {
		currentAnimation.update(deltaTime);
	}
	
	private void initialize() {
		switch(projectileType) {
		case Bullet:
			currentAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 1, 1, 1, 1, 1);
			speed = 20;
			minDamage = 4;
			maxDamage = 6;
			area = 1;
			break;
		}
	}

}
