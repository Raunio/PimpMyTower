package com.me.tdef.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.Constants;
import com.me.tdef.EntityPhysics;

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
	
	private Constants.TowerState currentTowerState;
	
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
		
		this.position = new Vector2(position.x + Constants.TILE_WIDTH - idleAnimation.getFrameWidth(), 
				position.y + Constants.TILE_HEIGHT - idleAnimation.getFrameHeight());
		
		activeProjectiles = new Array<Projectile>();
		
		scaleX = 1.2f;
		scaleY = 1.2f;
		rotation = 0f;
		this.rotationAcceleration = 0.25f;
		this.rotationMaxSpeed = 2f;
		this.attackRange = 120f;
		this.attackSpeed = 0.3f;
		
		rotationDirection = Constants.RotationDirection.None;
		currentEntityState = Constants.EntityState.Stopped;
		currentTowerState = Constants.TowerState.Idle;
		
		update(0f);
	}
	
	private void initializeAnimations(Texture spriteSheet) {
		idleAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 32, 32, 0, 1, 0);
		shootingAnimation = new EntityAnimation(spriteSheet, 0.025f, false, 32, 32, 0, 1, 0);
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
			activeProjectiles.add(new Projectile(projectileType, new Vector2(position.x + getOrigin().x, 
					position.y + getOrigin().y), rotation));
			currentTowerState = Constants.TowerState.Shooting;
			shootTimer = 0;
		}
	}
	
	/**
	 * Updates all projectiles shot by the turret.
	 */
	public void updateProjectiles(float deltaTime) {
		for(Projectile p : activeProjectiles) {
			p.update(deltaTime);
			EntityPhysics.instance().applyProjectilePhysics(p);
		}
	}
	
	/**
	 * Draw all active projectiels shot by the turret.
	 */
	public void drawProjectiles(SpriteBatch batch) {
		for(Projectile p : activeProjectiles) {
			p.draw(batch);
		}
	}
	
	/**
	 * Checks if any projectiles have left the game area and cleans them off.
	 */
	public void cleanProjectiles(float levelWidth, float levelHeight) {
		for(int i = 0; i < activeProjectiles.size; i++) {
			if(activeProjectiles.get(i).position.x < 0)
				activeProjectiles.removeIndex(i);
			else if(activeProjectiles.get(i).position.y < 0) 
				activeProjectiles.removeIndex(i);
			else if(activeProjectiles.get(i).position.x > levelWidth)
				activeProjectiles.removeIndex(i);
			else if(activeProjectiles.get(i).position.y > levelHeight) {
				activeProjectiles.removeIndex(i);
			}
		}
	}
	
	private void handleAnimations() {
		switch(currentTowerState) {
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
