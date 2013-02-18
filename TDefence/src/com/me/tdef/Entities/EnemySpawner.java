package com.me.tdef.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;
import com.me.tdef.Constants;

/** Class for spawning enemies.
 * @author Tuukka Teppola
 *
 */

public class EnemySpawner
{

	private Vector2 spawnTilePoint;
	private float enemyStartingRotation;
	
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
	 * Gets the current wave count.
	 */
	public int getWaveCount()
	{
		return waveCounter;
	}
	
	/**
	 * Gets the type of the enemy that is currently being spawned.
	 */
	public Constants.EnemyType getSpawningEnemyType()
	{
		return enemyType;
	}
	
	/**
	 * Gets the current spawn rate of the spawning enemies in seconds.
	 */
	public float getSpawnRate()
	{
		return spawnRate;
	}
	
	/**
	 * Gets the the length of the current wave in seconds.
	 */
	public float getWaveLength()
	{
		return waveTime;
	}
	
	/**
	 * Gets the waiting time between waves in seconds.
	 */
	public float getWaitingTime()
	{
		return waitingTime;
	}
	
	/**
	 * Sets the type of the enemy that is currently being spawned. InitNewWave() first if you want to start a hole new wave with a different kind of an enemy.
	 */
	public void setSpawningEnemyType(Constants.EnemyType newEnemyType)
	{
		this.enemyType = newEnemyType;
	}
	
	/**
	 * Sets the the length of the current wave in seconds.
	 */
	public void setWaveTime(float time)
	{
		waveTime = time;
	}
	
	/**
	 * Sets the waiting time between waves in seconds for the upcoming wave initializations.
	 */
	public void setWatingTime(float time)
	{
		waitingTime = time;
	}
	
	/**
	 * Sets the spawn rate in seconds.
	 */
	public void setSpawnRate(float time)
	{
		spawnRate = time;
	}
	
	
	/**
	 * The Constructor
	 * @param spawnTilePoint
	 * @param enemyStartingRotation
	 */
	public EnemySpawner(Vector2 spawnTilePoint, float enemyStartingRotation)
	{
		this.spawnTilePoint = spawnTilePoint;
		this.enemyStartingRotation = enemyStartingRotation;
		
		this.waveCounter = 1;
		this.maxWaves = 20;
		this.waveTimer = 0f;
		this.waveTime = 10.0f;
		this.waveTimeIncreasement = 5f;
		this.waitingTimer = 0f;
		this.waitingTime = 15f;
		
		this.spawnTimer = 0f;
		this.spawnRate = 3.0f;
		this.minSpawnRate = 0.5f;
		this.spawnRateDecreasement = 0.5f;
		
		spawnedEnemies = new Array<Enemy>();
		isWaiting = true;
		enemyType = Constants.EnemyType.fromInt(0);

	}
	
	/**
	 * Initializes a new wave and starts waiting for the start of it.
	 */
	public void InitNewWave(){
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
	 * Update method handles the function calls according to the time.
	 */
	public void Update(float deltaTime)
	{
				
		if(isWaiting)
		{			
			this.waitingTimer += deltaTime;
			
			if(waitingTimer >= waitingTime){
				isWaiting = false;
				waitingTimer = 0f;
				spawnTimer = 0f;
			}
		}
		else
		{
			this.waveTimer += deltaTime;
			this.spawnTimer += deltaTime;
			
			if(spawnTimer > spawnRate && waveCounter < maxWaves)
			{
				spawnTimer = 0f;
				SpawnEnemy();
			}
			
			if(waveTimer >= waveTime && waveCounter < maxWaves)
			{
				InitNewWave();	
			}
		}
		
	}
	
	/**
	 * Method for spawning an enemy.
	 */
	private void SpawnEnemy()
	{
		Enemy enemy = new Enemy(enemyType);
		enemy.setPosition(new Vector2(spawnTilePoint.x + enemy.getOrigin().x, spawnTilePoint.y + enemy.getOrigin().y));
		enemy.setRotation(enemyStartingRotation);
		enemy.setTargetRotation(enemyStartingRotation);
		
		spawnedEnemies.add(enemy);
	}
	
}