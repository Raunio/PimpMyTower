package com.me.tdef;

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
	}
	
    private void rotate(Entity subject)
    {
    	if(subject.getRotationDirection() == Constants.RotationDirection.Clockwise){
    		subject.setRotationVelocity(subject.getRotationVelocity() + subject.getRotationAcceleration());
    	}
    	else if(subject.getRotationDirection() == Constants.RotationDirection.CounterClockwise){
    		subject.setRotationVelocity(subject.getRotationVelocity() - subject.getRotationAcceleration());
    	}
    	else{
    		
    	}
    }
	

}
