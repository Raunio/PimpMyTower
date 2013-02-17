package com.me.tdef.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.tdef.Constants;
import com.me.tdef.Entities.Enemy;
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
	
	private Tile[][] tiles;
	
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
	 * Returns a 2-dimentional array of tiles specified for the map.
	 */
	public Tile[][] getTileArray() {
		return tiles;
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
	
	/**
	 * @param width Game level width
	 * @param height Game level height
	 */
	public GameMap(float width, float height) {
		mapWidth = width;
		mapHeight = height;		
	}
	
	/**
	 * Loads all textures.
	 */
	public void loadContent() {
		openPathTexture = new Texture(Gdx.files.internal(Constants.OpenPathTextureAsset));
		cornerPathTexture = new Texture(Gdx.files.internal(Constants.CornerPathTextureAsset));
		baseGroundTexture = new Texture(Gdx.files.internal(Constants.BaseGroundTextureAsset));
	}
	
	/**
	 * Dispose all textures.
	 */
	public void dispose() {
		openPathTexture.dispose();
		cornerPathTexture.dispose();
	}
	
	public void GuideEnemy(Enemy subject) {
		for(int i = 0; i < (int)(mapWidth / Constants.TILE_WIDTH); i++) {
			for(int j = 0; j < (int)(mapHeight / Constants.TILE_HEIGHT); j++) {
				if(tiles[i][j] != null && tiles[i][j].containsPoint(new Vector2(subject.getPosition().x + subject.getOrigin().x, 
						subject.getPosition().y + subject.getOrigin().y)) && tiles[i][j].getGuidePoint() != null) {
					subject.setTargetRotation(tiles[i][j].getGuidePoint().getGuideRotation());
				}
			}
		}
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
		
		for(int i = 0; i < (int)(mapWidth / Constants.TILE_WIDTH); i++) {
			for(int j = 0; j < (int)(mapHeight / Constants.TILE_HEIGHT); j++) {
				if(tiles[i][j] != null)
					tiles[i][j].draw(batch);
			}
		}
	}
	
	/**
	 * Creates a tilemap from given string data. Symbols are defined in Constants.java
	 */
	public void createMap(String[] data) {
		int rowCounter = 0;
		
		tiles = new Tile[(int) (mapWidth / Constants.TILE_WIDTH)][(int) (mapHeight / Constants.TILE_HEIGHT)];
		
		for(String s : data) {
			for(int i = 0; i < s.length(); i++) {	
				tiles[i][rowCounter] = new Tile(new Vector2(i * Constants.TILE_WIDTH, rowCounter * Constants.TILE_HEIGHT));
				tiles[i][rowCounter].createSprite(s.charAt(i));
			}
			
			rowCounter++;
		}
	}

}
