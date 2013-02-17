package com.me.tdef;

import com.me.tdef.Entities.Enemy;
import com.me.tdef.Entities.Entity;

/**
 * @author Niko
 *
 */
public class CharacterPhysics {
	
	private static CharacterPhysics instance;
	
	public static CharacterPhysics instance()
	{
		if(instance == null)
			instance = new CharacterPhysics();
		
		return instance;
	}
	
	public void apply(Entity subject){
		rotate(subject);
		
		subject.applyVelocities();
	}
	
    private void rotate(Entity subject) {
    	if(subject.getRotationDirection() == Constants.RotationDirection.Clockwise 
    			&& subject.getRotationVelocity() < subject.getRotationMaxSpeed()){
    		
    		subject.setRotationVelocity(subject.getRotationVelocity() + subject.getRotationAcceleration());
    	}
    	else if(subject.getRotationDirection() == Constants.RotationDirection.CounterClockwise
    			&& subject.getRotationVelocity() > -subject.getRotationMaxSpeed()){
    		
    		subject.setRotationVelocity(subject.getRotationVelocity() - subject.getRotationAcceleration());
    	}
    	else{
    		
    		if(subject.getRotationVelocity() > subject.getRotationAcceleration()) {
    			subject.setRotationVelocity(subject.getRotationVelocity() - subject.getRotationAcceleration());
    		}
    		else if(subject.getRotationVelocity() < -subject.getRotationAcceleration()) {
    			subject.setRotationVelocity(subject.getRotationVelocity() + subject.getRotationAcceleration());
    		}
    		else {
    			subject.setRotationVelocity(0f);
    		}
    	}
    }
	
    private void walk(Enemy subject) {
    	
    }
}
