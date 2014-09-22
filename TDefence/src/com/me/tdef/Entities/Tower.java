package com.me.tdef.Entities;

import com.badlogic.gdx.Gdx;
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
	private float attackSpeed;
	
	private Array<Projectile> activeProjectiles;
	
	private EntityAnimation idleAnimation;
	private EntityAnimation shootingAnimation;
	
	private Constants.TowerState currentTowerState;
	
	private float shootTimer;
	
	private TowerBarrel mainBarrel;
	private TowerHull hull;

	private static Texture spriteSheet;
	
	/**
	 * Returns the attack range of the tower in pixels.
	 */
	public float getAttackRange() {
		return 150f;
	}
	
	/**
	 * Returns an array projectiles shot by the turret.
	 */
	public Array<Projectile> getActiveProjectiles() {
		return activeProjectiles;
	}
	
	public Tower(int[] code, Vector2 position) {	
		initializeAnimations();
		
		this.position = new Vector2(position.x, position.y);
		
		activeProjectiles = new Array<Projectile>();
	
		rotationDirection = Constants.RotationDirection.None;
		currentEntityState = Constants.EntityState.Stopped;
		currentTowerState = Constants.TowerState.Idle;	
		
		currentAnimation.update(0f);
		
		mainBarrel = new TowerBarrel(Constants.TowerBarrelType.Basic, 
				new Texture(Gdx.files.internal(Constants.TowerBarrelSheetAsset)),
				new Vector2(this.position.x, this.position.y),
				this.rotation);
		
		mainBarrel.setProjectile(code);
		
		hull = new TowerHull(spriteSheet, this.position, rotation, 32, 32, 100);
		hull.setScale(1f, 1f);

		this.attackSpeed = 0.3f;
		
		initializeStats();
	}
	
	public static void loadSpriteSheet(String path) {
		spriteSheet = new Texture(Gdx.files.internal(path));
	}
	
	private void initializeAnimations() {
		idleAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 32, 32, 0, 1, 0);
		shootingAnimation = new EntityAnimation(spriteSheet, 0.025f, false, 32, 32, 0, 1, 0);
		
		currentAnimation = idleAnimation;
	}
	
	private void initializeStats() {
		rotationMaxSpeed = 3f;
		rotationAcceleration = 0.5f;
	}
	
	/**
	 * Main update method.
	 */
	public void update(float deltaTime){
		handleAnimations();
		currentAnimation.update(deltaTime);
		updateRotation();
		shootTimer += deltaTime;
		
		mainBarrel.update(deltaTime, rotation);
		hull.update(deltaTime, rotation);
	}
	
	/**
	 * Shoots a projectile if the turrets attack is ready.
	 */
	public void shoot() {
		if(isAttackReady()) {
			activeProjectiles.add(mainBarrel.getProjectile());
			mainBarrel.playShootingAnimation();
			hull.playShootingAnimation();
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
	private void drawProjectiles(SpriteBatch batch) {
		for(Projectile p : activeProjectiles) {
			p.draw(batch);
		}
	}
	
	private void drawBarrels(SpriteBatch batch) {
		mainBarrel.draw(batch);
	}
	
	private void drawHull(SpriteBatch batch) {
		hull.draw(batch);
	}
	
	@Override
	public void draw(SpriteBatch batch) {		
		drawProjectiles(batch);
		drawBarrels(batch);
		drawHull(batch);
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
