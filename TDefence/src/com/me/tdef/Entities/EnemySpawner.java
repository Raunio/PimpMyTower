package com.me.tdef.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;
import com.me.tdef.Constants;

/** Class for spawning enemies.
 * @author Tuukka
 *
 */

public class EnemySpawner
{

	private Vector2 spawnTilePoint;
	private float rotation;
	
	private int waveCounter;
	private int maxWaves;
	private float waveTimer;
	private float waveTime;
	private float waveTimeIncreasement;
	private float waitingTimer;
	private float waitingTime;
	private boolean isWaiting;
	
	private float spawnTimer;
	private float spawnRate;
	private float minSpawnRate;
	private float spawnRateDecreasement;
	
	public Array<Enemy> spawnedEnemies;
	private Constants.EnemyType enemyType;

	
	
	/**
	 * Gets the type of the enemy that is currently being spawned
	 */
	public Constants.EnemyType  getSpawningEnemyType()
	{
		return enemyType;
	}
	
	/**
	 * Gets the spawn rate of the spawning enemies
	 */
	public float getSpawnRate()
	{
		return spawnRate;
	}
	
	/**
	 * Gets the the length of the current wave
	 */
	public float getWaveTime()
	{
		return waveTime;
	}
	
	/**
	 * Gets the waiting time between waves
	 */
	public float getTimeBetweenWaves()
	{
		return waitingTime;
	}
	
	/**
	 * Sets the type of the enemy that is currently being spawned
	 */
	public void setSpawningEnemyType(Constants.EnemyType newEnemyType)
	{
		this.enemyType = newEnemyType;
	}
	
	/**
	 * Sets the the length of the current wave
	 */
	public void setWaveTime(float time)
	{
		waveTime = time;
	}
	
	/**
	 * Sets the waiting time between waves
	 */
	public void setTimeBetweenWaves(float time)
	{
		waitingTime = time;
	}
	
	/**
	 * The constructor
	 */
	public EnemySpawner(Vector2 spawnTilePoint, float rotation)
	{
		this.spawnTilePoint = spawnTilePoint;
		this.rotation = rotation;
		
		this.waveCounter = 1;
		this.maxWaves = 10;
		this.waveTimer = 0f;
		this.waveTime = 60000.0f;
		this.waveTimeIncreasement = 10000f;
		this.waitingTimer = 0f;
		this.waitingTime = 5000f;
		
		this.spawnTimer = 0f;
		this.spawnRate = 3000.0f;
		this.minSpawnRate = 500f;
		this.spawnRateDecreasement = 500f;
		
		spawnedEnemies = new Array<Enemy>();
		isWaiting = false;
		enemyType = Constants.EnemyType.fromInt(0);

	}
	
	private void InitWave(){
		waveTimer = 0f;
		waveCounter++;
		waveTime += waveTimeIncreasement;
		isWaiting = true;
		
		if(spawnRate > minSpawnRate){
			spawnRate -= spawnRateDecreasement;
		}
		
		if(waveCounter > Constants.EnemyType.size)
		{
			enemyType = Constants.EnemyType.fromInt(Constants.EnemyType.size - 1);
		}
		else
		{
			enemyType = Constants.EnemyType.fromInt(waveCounter - 1);
		}	
	}
	
	/**
	 * Update method
	 */
	public void Update(float deltaTime)
	{
		this.waveTimer += deltaTime;
		this.spawnTimer += deltaTime;
		
		if(isWaiting)
		{			
			this.waitingTimer += deltaTime;
			
			if(waitingTimer >= waitingTime){
				isWaiting = false;
				waitingTimer = 0f;
				spawnTimer = 0f;
			}
		}
		
		if(spawnTimer > spawnRate && waveCounter < maxWaves)
		{
			spawnTimer = 0f;
			SpawnEnemy();
		}
		
		if(waveTimer >= waveTime && waveCounter < maxWaves)
		{
			InitWave();		
		}
		
		
	}
	
	/**
	 * Method for spawning an enemy.
	 */
	private void SpawnEnemy()
	{
		Enemy enemy = new Enemy(enemyType);
		enemy.setPosition(new Vector2(spawnTilePoint.x + enemy.getOrigin().x, spawnTilePoint.y + enemy.getOrigin().y));
		enemy.setRotation(rotation);
		enemy.setTargetRotation(rotation);
		
		spawnedEnemies.add(enemy);
	}
	
}