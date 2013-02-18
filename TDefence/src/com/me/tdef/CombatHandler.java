package com.me.tdef;

import com.badlogic.gdx.utils.Array;
import com.me.tdef.Entities.Enemy;
import com.me.tdef.Entities.Tower;

public class CombatHandler {
	
	private static CombatHandler instance;
	
	public static CombatHandler instance() {
		if(instance == null) {
			instance = new CombatHandler();
		}
		
		return instance;
	}
	
	public void update(Array<Tower> towers, Array<Enemy> enemies) {
		for(int t = 0; t < towers.size; t++) {
			for(int p = 0; p < towers.get(t).getActiveProjectiles().size; p++) {
				for(int e = 0; e < enemies.size; e++) {
					if(enemies.get(e).getBoundingBox().contains(towers.get(t).getActiveProjectiles().get(p).getBoundingBox()) && enemies.get(e).getCurrentHealth() > 0) {
						enemies.get(e).applyDamage(towers.get(t).getActiveProjectiles().get(p).getMaxDamage());
						towers.get(t).getActiveProjectiles().removeIndex(p);
						break;
					}
					
					if(enemies.get(e).getCurrentHealth() <= 0) {
						enemies.removeIndex(e);
						break;
					}
				}
			}
		}
	}

}
