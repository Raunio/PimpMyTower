package com.me.tdef.Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.Entities.Enemy;
import com.me.tdef.Entities.Tower;

/**
 * Holds all game objects and logic.
 *
 */
public class GameLevel {
	
	private Array<Enemy> enemies;
	private Array<Tower> towers;
	
	/**
	 * Initializes the level.
	 */
	public void create(){
		enemies = new Array<Enemy>();
		towers = new Array<Tower>();
	}
	
	/**
	 * Main update method. Holds all game logic.
	 */
	public void update(float deltaTime){
		for(Enemy e : enemies){
			e.update(deltaTime);
		}
		
		for(Tower t : towers){
			t.update(deltaTime);
		}
	}
	
	/**
	 * Main draw method.
	 */
	public void render(SpriteBatch batch){
		for(Enemy e : enemies){
			e.draw(batch);
		}
		
		for(Tower t : towers){
			t.draw(batch);
		}
	}

}
