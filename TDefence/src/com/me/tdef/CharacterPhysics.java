package com.me.tdef;

/**
 * @author Niko
 *
 */
public class CharacterPhysics {
	
	private static CharacterPhysics instance;
	
	public static CharacterPhysics Instance()
	{
		if(instance == null)
			instance = new CharacterPhysics();
		
		return instance;
	}
	

}
