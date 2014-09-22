package com.me.tdef;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.Entities.Enemy;
import com.me.tdef.Entities.Tower;

public class CombatHandler {
	
	private static CombatHandler instance;
	
	private int totalCredits;
	
	public static CombatHandler instance() {
		if(instance == null) {
			instance = new CombatHandler();
		}
		
		return instance;
	}
	
	public int getTotalCredits() {
		return totalCredits;
	}
	
	public void setTotalCredits(int value) {
		totalCredits = value;
	}
	
	public void update(Array<Tower> towers, Array<Enemy> enemies, float deltaTime) {
		for(int t = 0; t < towers.size; t++) {
			for(int p = 0; p < towers.get(t).getActiveProjectiles().size; p++) {
				for(int e = 0; e < enemies.size; e++) {
					if(enemies.get(e).getBoundingBox().contains(towers.get(t).getActiveProjectiles().get(p).getBoundingBox()) && enemies.get(e).getCurrentHealth() > 0) {
						
						Random random = new Random();
						
						int min = towers.get(t).getActiveProjectiles().get(p).getMinDamage();
						int max = towers.get(t).getActiveProjectiles().get(p).getMaxDamage();
						
						int damage = Constants.getRandomInteger(min, max, random);
						
						enemies.get(e).applyDamage(damage);
						
						GraphicalUI.displayFloatingText("" + damage, new Vector2(enemies.get(e).getPosition().x + enemies.get(e).getOrigin().x, 
								enemies.get(e).getPosition().y + enemies.get(e).getOrigin().y), 0.5f);
						
						towers.get(t).getActiveProjectiles().removeIndex(p);
						break;
					}
					
					if(enemies.get(e).getCurrentHealth() <= 0) {	
						totalCredits += enemies.get(e).getBounty();
						enemies.removeIndex(e);
						break;
					}
				}
			}
		}
	}

}
