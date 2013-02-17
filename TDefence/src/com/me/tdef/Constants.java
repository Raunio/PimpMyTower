package com.me.tdef;

public class Constants {
	public static final int TILE_WIDTH = 64;
	public static final int TILE_HEIGHT = 64;
	
	public static final String TowerTextureAsset = "data/tower.png";
	public static final String OpenPathTextureAsset = "data/openPathTexture.png";
	public static final String CornerPathTextureAsset = "data/cornerPathTexture.png";
	public static final String BaseGroundTextureAsset = "data/baseGroundTexture.png";
	public static final String BulletTextureAsset = "data/bullet.png";
	public static final String ZombieSpriteSheetAsset = "data/enemy2.png";
	
	public static final String buttonUpTexture = "data/buttonUp.png";
	public static final String buttonDownTexture = "data/buttonDown.png";
	
	
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
	
	public static final String[] map1Data = {"Y LXXXA", "Y Y   Y", "Y Y aX<", "Y Y Y", ">XV >XX"};

	
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
	
	public enum ProjectileType {
		Bullet,
	}
	
	public enum ToolType {
		Select,
		Build,
	}
	
	public enum EnemyType {
		Zombie,
	}

}
