package com.me.tdef.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.CharacterPhysics;
import com.me.tdef.Constants;
import com.me.tdef.Entities.Enemy;
import com.me.tdef.Entities.Tower;
import com.me.tdef.Entities.TowerBehaviour;

/**
 * Holds all game objects and logic.
 *
 */
public class GameLevel {
	
	private Array<Enemy> enemies;
	public Array<Tower> towers;
	
	private TowerBehaviour towerBehaviour;
	
	private GameMap map;
	
	/**
	 * Initializes the level.
	 */
	public void create(){
		enemies = new Array<Enemy>();
		towers = new Array<Tower>();
		
		towerBehaviour = new TowerBehaviour();
		
		towers.add(new Tower(new Texture(Gdx.files.internal(Constants.TowerTextureAsset)), new Vector2(100, 100)));
		
		map = new GameMap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		map.loadContent();
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
			towerBehaviour.apply(t, enemies);
			t.updateProjectiles(deltaTime);
			CharacterPhysics.instance().apply(t);
		}
	}
	
	/**
	 * Main draw method.
	 */
	public void render(SpriteBatch batch){
		map.draw(batch);
		for(Enemy e : enemies){
			e.draw(batch);
		}
		
		for(Tower t : towers){
			t.draw(batch);
			t.drawProjectiles(batch);
		}
	}
	
	public void handleInput(int x, int y) {
		for(Tower t : towers) {
			t.setFacingPoint(new Vector2(x, Gdx.graphics.getHeight() - y));
		}
	}
	
	public void dispose() {
		map.dispose();
	}

}
