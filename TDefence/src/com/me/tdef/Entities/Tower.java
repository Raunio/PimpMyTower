package com.me.tdef.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.Constants;

/**
 * @author Niko
 *
 */
public class Tower extends Entity {
	private float attackRange;
	private float attackSpeed;
	
	private Constants.ProjectileType projectileType;
	
	private Array<Projectile> activeProjectiles;
	
	private EntityAnimation idleAnimation;
	private EntityAnimation shootingAnimation;
	
	private Constants.TowerState currentState;
	
	private float shootTimer;

	
	/**
	 * Returns the attack range of the tower in pixels.
	 */
	public float getAttackRange() {
		return attackRange;
	}
	
	/**
	 * Returns an array projecitles shot by the turret.
	 */
	public Array<Projectile> getActiveProjectiles() {
		return activeProjectiles;
	}
	
	public Tower(Texture spriteSheet, Vector2 position) {
		projectileType = Constants.ProjectileType.Bullet;
		initializeAnimations(spriteSheet);
		this.position = position;
		currentState = Constants.TowerState.Idle;
		activeProjectiles = new Array<Projectile>();
		scaleX = 1f;
		scaleY = 1f;
		rotation = 0f;
		this.rotationAcceleration = 0.25f;
		this.rotationMaxSpeed = 2f;
		rotationDirection = Constants.RotationDirection.None;
	}
	
	private void initializeAnimations(Texture spriteSheet) {
		idleAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 64, 64, 0, 1, 0);
		shootingAnimation = new EntityAnimation(spriteSheet, 0.025f, false, 64, 64, 0, 1, 0);
	}
	
	/**
	 * Main update method.
	 */
	public void update(float deltaTime){
		handleAnimations();
		currentAnimation.update(deltaTime);
		updateRotation();
		shootTimer += deltaTime;
	}
	
	/**
	 * Shoots a projectile if the turrets attack is ready.
	 */
	public void shoot() {
		if(isAttackReady()) {
			activeProjectiles.add(new Projectile(projectileType, position, rotation));
			currentState = Constants.TowerState.Shooting;
		}
	}
	
	/**
	 * Updates all projectiles shot by the turret.
	 */
	public void updateProjectiles(float deltaTime) {
		for(Projectile p : activeProjectiles) {
			p.update(deltaTime);
		}
	}
	
	public void drawProjectiles(SpriteBatch batch) {
		for(Projectile p : activeProjectiles) {
			p.draw(batch);
		}
	}
	
	private void handleAnimations() {
		switch(currentState) {
		case Idle:
			currentAnimation = idleAnimation;
			break;
		case Shooting:
			currentAnimation = shootingAnimation;
			break;
		}
	}
	
	private boolean isAttackReady() {
		return shootTimer >= attackSpeed ? true : false;
	}

}
