package com.me.tdef.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.me.tdef.Constants;

public class Enemy extends Entity{
	
	private float currentHealth;
	private float maxHealth;
	
	private Constants.EnemyType enemyType;
	
	private EntityAnimation walkingAnimation;
	private EntityAnimation stoppedAnimation;
	
	private Texture spriteSheet;
	
	/**
	 * Returns the current health of the enemy.
	 */
	public float getCurrentHealth() {
		return currentHealth;
	}
	
	/**
	 * Decreases the current health of the enemy by an amount.
	 */
	public void applyDamage(float amount) {
		currentHealth -= amount;
	}
	
	public Enemy(Constants.EnemyType enemyType) {
		this.enemyType = enemyType;
		this.scaleX = 1f;
		this.scaleY = 1f;
		
		this.currentEntityState = Constants.EntityState.Moving;
		
		initAnimations();
		initStats();
		
		currentHealth = maxHealth;
		
		currentAnimation = stoppedAnimation;
		currentAnimation.update(0f);
	}
	
	private void initAnimations() {
		switch(enemyType) {
		case Zombie:
			spriteSheet = new Texture(Gdx.files.internal(Constants.ZombieSpriteSheetAsset));
			walkingAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 32, 32, 0, 1, 0);
			stoppedAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 32, 32, 0, 1, 0);
			break;
		}
	}
	
	private void initStats() {
		switch(enemyType) {
		case Zombie:
			this.maxHealth = 37;
			this.acceleration = 0.1f;
			this.tangentialVelocityMax = 1f;
			this.rotationAcceleration = 0.5f;
			this.rotationMaxSpeed = 3f;
			break;
		}
	}
	
	private void handleAnimations() {
		switch(getCurrentEntityState()) {
		case Moving:
			currentAnimation = walkingAnimation;
			break;
		case Stopped:
			currentAnimation = stoppedAnimation;
			break;
		}		
	}
	
	public void update(float deltaTime){
		handleAnimations();
		
		
		currentAnimation.update(deltaTime);
		this.updateRotation();
	}
	
	public void dispose() {
		spriteSheet.dispose();
	}
}
