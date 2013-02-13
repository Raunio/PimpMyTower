package com.me.tdef.Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.Entities.Enemy;
import com.me.tdef.Entities.Tower;

public class GameLevel {
	
	private Array<Enemy> enemies;
	private Array<Tower> towers;
	
	public void create(){
		enemies = new Array<Enemy>();
		towers = new Array<Tower>();
	}
	
	public void update(float deltaTime){
		for(Enemy e : enemies){
			e.update(deltaTime);
		}
		
		for(Tower t : towers){
			t.update(deltaTime);
		}
	}
	
	public void render(SpriteBatch batch){
		for(Enemy e : enemies){
			e.draw(batch);
		}
		
		for(Tower t : towers){
			t.draw(batch);
		}
	}

}
