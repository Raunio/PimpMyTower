package com.me.tdef.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.EntityPhysics;
import com.me.tdef.Constants;
import com.me.tdef.Entities.Enemy;
import com.me.tdef.Entities.Projectile;
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
	
		map = new GameMap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		map.loadContent();
		map.createMap(Constants.map1Data);
		
		buildTower(1, 3);
		spawnEnemy(Constants.EnemyType.Zombie, 0, 0, 90);
		
		Projectile.loadSpriteSheet(new Texture(Gdx.files.internal(Constants.BulletTextureAsset)));
	}
	
	/**
	 * Main update method. Holds all game logic.
	 */
	public void update(float deltaTime){
		for(Enemy e : enemies){
			e.update(deltaTime);
			EntityPhysics.instance().apply(e);
			map.GuideEnemy(e);
		}
		
		for(Tower t : towers){
			t.update(deltaTime);
			towerBehaviour.apply(t, enemies);
			t.updateProjectiles(deltaTime);
			EntityPhysics.instance().apply(t);
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
			t.drawProjectiles(batch);
			t.draw(batch);	
		}			
	}
	
	public void handleInput(int x, int y) {

	}
	
	public void dispose() {
		map.dispose();
	}
	
	private void buildTower(int x, int y) {
		if(!map.getTileArray()[x][y].isOccupied()) {
			towers.add(new Tower(new Texture(Gdx.files.internal(Constants.TowerTextureAsset)), map.getTileArray()[x][y].getPosition()));
			map.getTileArray()[x][y].setOccupied(true);
		}
	}
	
	private void spawnEnemy(Constants.EnemyType enemyType, int x, int y, float rotation) {
		Enemy e = new Enemy(enemyType);
		Vector2 pos = map.getTileArray()[x][y].getPosition();
		
		e.setPosition(new Vector2(pos.x + e.getOrigin().x, pos.y + e.getOrigin().y));
		e.setRotation(rotation);
		e.setTargetRotation(rotation);
		
		enemies.add(e);
	}

}
