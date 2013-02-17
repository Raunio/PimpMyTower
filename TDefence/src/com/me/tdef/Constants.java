package com.me.tdef;

public class Constants {
	
	public static final String TowerTextureAsset = "data/tower.png";
	public static final String OpenPathTextureAsset = "data/openPathTexture.png";
	public static final String CornerPathTextureAsset = "data/cornerPathTexture.png";
	public static final String BaseGroundTextureAsset = "data/baseGroundTexture.png";
	
	public enum RotationDirection{
		Clockwise,
		CounterClockwise,
		None,
	}
	
	public enum EntityState {
		Stopped,
		Walking,
		Disabled,
		Dead,
	}
	
	public enum TowerState {
		Idle,
		Shooting,
	}
	
	public enum ProjectileType {
		Bullet,
	}

}
