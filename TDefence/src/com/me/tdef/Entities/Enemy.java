package com.me.tdef.Entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.me.tdef.Constants;

public class Enemy extends Entity{
	
	private float currentHealth;
	private float maxHealth;
	private int bounty;
	private int toughness;
	private int evasion;
	private int resilience;
	
	
	private Constants.EnemyType enemyType;
	
	private EntityAnimation walkingAnimation;
	private EntityAnimation stoppedAnimation;
	
	private static Texture spriteSheet;
	
	private Constants.EnemyOrientation orientation;
	
	/**
	 * Returns the current health of the enemy.
	 */
	public float getCurrentHealth() {
		return currentHealth;
	}
	
	public int getBounty() {
		return bounty;
	}
	
	public int getToughness() {
		return toughness;
	}
	
	public int getEvasion() {
		return evasion;
	}
	
	public int getResilience() {
		return resilience;
	}
	
	public Constants.EnemyOrientation getOrientation() {
		return orientation;
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
	
	public static void loadSpriteSheet(String path) {
		spriteSheet = new Texture(Gdx.files.internal(path));
	}
	
	private void initAnimations() {
		switch(enemyType) {
		case Zombie:
			walkingAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 32, 32, 0, 1, 0);
			stoppedAnimation = new EntityAnimation(spriteSheet, 0.025f, true, 32, 32, 0, 1, 0);
			break;
		}
	}
	
	private void initStats() {
		Random r = new Random();
		switch(enemyType) {
		case Zombie:
			this.maxHealth = Constants.getRandomInteger(30, 40, r);
			this.acceleration = 0.1f;
			this.tangentialVelocityMax = Constants.getRandomFloat(0.85f, 1f, r);
			this.rotationAcceleration = 0.5f;
			this.rotationMaxSpeed = Constants.getRandomFloat(0.7f, 1.1f, r);
			this.bounty = 2;
			this.toughness = 0;
			this.evasion = 0;
			this.resilience = 0;
			this.orientation = Constants.EnemyOrientation.Ground;
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
