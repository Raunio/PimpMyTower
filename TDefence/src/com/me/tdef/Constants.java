package com.me.tdef;

import java.util.Random;



public class Constants {
	public static final int TILE_WIDTH = 128;
	public static final int TILE_HEIGHT = 128;
	
	public static final String TowerTextureAsset = "data/firesentry.png";
	public static final String OpenPathTextureAsset = "data/openPathTexture.png";
	public static final String CornerPathTextureAsset = "data/cornerPathTexture.png";
	public static final String BaseGroundTextureAsset = "data/baseGroundTexture.png";
	public static final String BulletTextureAsset = "data/bullet.png";
	public static final String FireBoltTextureAsset = "data/firebolt.png";
	public static final String TowerBarrelSheetAsset = "data/towerBarrel.png";
	public static final String ZombieSpriteSheetAsset = "data/enemy2.png";
	
	public static final String buttonUpTexture = "data/buttonUp.png";
	public static final String buttonDownTexture = "data/buttonDown.png";
	
	public static final String SelectToolTextureAsset = "data/selection128.png";
	
	
	public static final char OpenPathSymbolX = 'X';
	public static final char OpenPathSymbolY = 'Y';
	
	public static final char CornerPathSymbol_topRight = '>';
	public static final char CornerPathSymbol_topLeft = '<';
	public static final char CornerPathSymbol_botRight = 'L';
	public static final char CornerPathSymbol_botLeft = 'J';
	
	public static final char CornerPathSymbol_rightTop = 'A';
	public static final char CornerPathSymbol_rightBot = 'V';
	public static final char CornerPathSymbol_leftTop = 'a';
	public static final char CornerPathSymbol_leftBot = 'v';
	
	public static final String[] map1Data = {"Y LXXXA LXXA", 
											 "Y Y   Y Y  Y", 
											 "Y Y aX< Y  Y", 
											 "Y Y Y   Y  Y", 
											 ">XV >XXXV  Y", 
											 "           Y", 
											 "XXXXXXXXXXX<"};

	
	public enum RotationDirection{
		Clockwise,
		CounterClockwise,
		None,
	}
	
	public enum EntityState {
		Stopped,
		Moving,
		Disabled,
	}
	
	public enum TowerState {
		Idle,
		Shooting,
	}
	
	public enum ToolType {
		Select,
		Build,
	}
	
	public enum SentryType {
		Lightning,
		Fire,
		Cryo,
		Stone,
		Light,
		Dark
	}
	
	public enum TowerBarrelType {
		Basic,
		Heavy,
		Double,
		Artillery,
	}
	
	public enum EnemyOrientation {
		Air,
		Ground,
	}
	
	
	public enum EnemyType {
		Zombie;
		
		private static EnemyType[] values = null;
	    public static EnemyType fromInt(int i) {
	        if(EnemyType.values == null) {
	        	EnemyType.values = EnemyType.values();
	        }
	        return EnemyType.values[i];
	    }
	    public static final int size = EnemyType.values().length;
	}
	
	public enum MajorModType {
		AOE, Pass, Fast, Slow, Gas;
		private static MajorModType[] values = null;
		public static MajorModType fromInt(int i) {
			if(MajorModType.values == null) {
				MajorModType.values = MajorModType.values();
			}
			
			return MajorModType.values[i];
		}
	}
	
	public static int getRandomInteger(int aStart, int aEnd, Random aRandom){
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    return  (int)(fraction + aStart);    
	  }
	public static float getRandomFloat(float aStart, float aEnd, Random aRandom) {
		if ( aStart > aEnd ) {
		      throw new IllegalArgumentException("Start cannot exceed End.");
		    }
		    //get the range, casting to long to avoid overflow problems
		    float range = aEnd - aStart;
		    // compute a fraction of the range, 0 <= frac < range
		    float fraction = (float)(range * aRandom.nextDouble());
		    return  (float)(fraction + aStart);
	}
}
