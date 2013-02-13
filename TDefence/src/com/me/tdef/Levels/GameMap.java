package com.me.tdef.Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.tdef.Entities.EnemyGuidePoint;

/**
 * A drawable ingame map. Also contains all enemy guidepoints;
 *
 */
public class GameMap {
	
	private float mapWidth;
	private float mapHeight;
	
	private EnemyGuidePoint[] guidePoints;
	
	private Texture baseGroundTexture;
	private Texture openPathTexture;
	private Texture cornerPathTexture;
	
	private Sprite openPathY;
	private Sprite openPathX;
	private Sprite cornerPathYR;
	private Sprite cornerPathYL;
	private Sprite cornerPathXU;
	private Sprite cornerPathXD;
	private Sprite[] mainPath;
	
	/**
	 * Gets the width of the map.
	 */
	public float getMapWidth() {
		return mapWidth;
	}
	/**
	 * Gets the height of the map.
	 */
	public float getMapHeight() {
		return mapHeight;
	}
	/**
	 * Gets all of the maps guidepoints in an array.
	 */
	public EnemyGuidePoint[] getGuidePoints() {
		return guidePoints;
	}
	
	/**
	 * Sets the base ground texture for the map. This is a texture that drawn to the lowest layer.
	 */
	public void setBaseGroundTexture(Texture texture) {
		baseGroundTexture = texture;
	}
	
	/**
	 * Sets the open path texture for the map. This texture is used for drawing the main path of the map.
	 */
	public void setOpenPathTexture(Texture texture) {
		openPathTexture = texture;
	}
	
	/**
	 * Sets the texture for game path corners.
	 */
	public void setCornerPathTexture(Texture texture) {
		cornerPathTexture = texture;
	}
	
	/**
	 * Sets the guide points of the map.
	 */
	public void setGuidePoints(EnemyGuidePoint[] guidePoints) {
		this.guidePoints = guidePoints;
	}
	
	public GameMap(float width, float height) {
		mapWidth = width;
		mapHeight = height;
	}
	
	public void initialize() {
		
	}
	
	/**
	 * Main draw method. Call in the render() of the game to draw map.
	 */
	public void draw(SpriteBatch batch) {
		
		int rows = (int)mapHeight / (int)baseGroundTexture.getHeight() + 1;
		int cols = (int)mapWidth / (int)baseGroundTexture.getWidth() + 1;
		
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				batch.draw(baseGroundTexture, i * baseGroundTexture.getWidth(), j * baseGroundTexture.getHeight());
			}
		}
		
		for(Sprite s : mainPath) {
			s.draw(batch);
		}
	}

}
