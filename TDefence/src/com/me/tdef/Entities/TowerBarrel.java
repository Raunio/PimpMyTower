package com.me.tdef.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.tdef.Constants;

public class TowerBarrel extends Entity {
	private Texture spriteSheet;
	private int[] projectileCode;
	private Vector2 shootPoint;
	private Constants.TowerBarrelType type;
	
	private EntityAnimation idleAnimation;
	private EntityAnimation shootingAnimation;
	
	private float mass;
	
	public Projectile getProjectile() {
		int radius = currentAnimation.getFrameWidth();
		double rads = Math.toRadians(rotation);
		int fullLength = Math.round(radius);
		
		this.shootPoint = new Vector2(position.x + (float)Math.cos(rads) * fullLength, 
				position.y + (float)Math.sin(rads) * fullLength);
		
		return new Projectile(projectileCode, shootPoint, rotation);
	}
	
	/**
	 * Returns the spawning point for projectiles.
	 */
	public Vector2 getShootingPoint() {
		return shootPoint;
	}
	
	/**
	 * Returns the mass of the barrel.
	 */
	public float getMass() {
		return mass;
	}
	
	public void setProjectile(int[] code) {
		this.projectileCode = code;
	}
	
	public TowerBarrel(Constants.TowerBarrelType type, Texture spriteSheet, Vector2 position, float rotation) {
		this.spriteSheet = spriteSheet;
		this.type = type;
		initialize();
		currentAnimation.update(0f);
		
		this.setOrigin(0, currentAnimation.getFrameHeight() / 2);
		
		this.position = new Vector2(position.x, position.y - this.getOrigin().y);
		this.rotation = rotation;		
	}
	
	public void update(float deltaTime, float towerRotation) {
		this.rotation = towerRotation;
		handleAnimations();
		
		currentAnimation.update(deltaTime);
	}
	
	public void playShootingAnimation() {
		shootingAnimation.update(0f);
		shootingAnimation.resetAnimation();
		currentAnimation = shootingAnimation;
	}
	
	private void initialize() {
		switch(type) {
		case Basic:
			idleAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 32, 8, 0, 1, 0);
			shootingAnimation = new EntityAnimation(spriteSheet, 0.025f, false, 32, 8, 0, 1, 0);
			this.scaleX = 1f;
			this.scaleY = 1f;
			this.mass = 10;
			break;
		}
		
		currentAnimation = idleAnimation;
	}
	
	private void handleAnimations() {
		if(currentAnimation == shootingAnimation) {
			if(shootingAnimation.isAnimationFinished()) {
				currentAnimation = idleAnimation;
			}
		}
	}
}
