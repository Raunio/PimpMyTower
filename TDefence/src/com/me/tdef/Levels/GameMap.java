package com.me.tdef.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.Constants;
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
	private Sprite cornerPathYRFlipped;
	private Sprite cornerPathYLFlipped;
	private Sprite cornerPathXU;
	private Sprite cornerPathXD;
	private Sprite cornerPathXUFlipped;
	private Sprite cornerPathXDFlipped;
	private Array<Sprite> mainPath;
	
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
		
		mainPath = new Array<Sprite>();
		
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
	
	public void createMap(String[] data) {
		int rowCounter = 0;
		
		int cWidth = cornerPathTexture.getWidth();
		int cHeight = cornerPathTexture.getHeight();
		int oWidth = openPathTexture.getWidth();
		int oHeight = openPathTexture.getHeight();
		
		for(String s : data) {
			for(int i = 0; i < s.length(); i++) {
				Sprite sprite = new Sprite();
				switch(s.charAt(i)) {
				case Constants.CornerPathSymbol_botLeft:
					sprite = new Sprite(cornerPathTexture);
					sprite.rotate(180);
					sprite.setPosition(i * cWidth, rowCounter * cHeight);
					mainPath.add(sprite);
					break;
				case Constants.CornerPathSymbol_botRight:
					sprite = new Sprite(cornerPathTexture);
					sprite.rotate(180);
					sprite.flip(true, false);
					sprite.setPosition(i * cWidth, rowCounter * cHeight);
					mainPath.add(sprite);
					break;
				case Constants.CornerPathSymbol_leftBot:
					sprite = new Sprite(cornerPathTexture);
					sprite.rotate(270);
					sprite.flip(false, true);
					sprite.setPosition(i * cWidth, rowCounter * cHeight);
					mainPath.add(sprite);
					break;
				case Constants.CornerPathSymbol_leftTop:
					sprite = new Sprite(cornerPathTexture);
					sprite.rotate(0);
					sprite.flip(false, true);
					sprite.setPosition(i * cWidth, rowCounter * cHeight);
					mainPath.add(sprite);
					break;
				case Constants.CornerPathSymbol_rightBot:
					sprite = new Sprite(cornerPathTexture);
					sprite.rotate(-90);
					sprite.setPosition(i * cWidth, rowCounter * cHeight);
					mainPath.add(sprite);
					break;
				case Constants.CornerPathSymbol_rightTop:
					sprite = new Sprite(cornerPathTexture);
					sprite.rotate(90);
					sprite.flip(false, true);
					sprite.setPosition(i * cWidth, rowCounter * cHeight);
					mainPath.add(sprite);
					break;
				case Constants.CornerPathSymbol_topLeft:
					sprite = new Sprite(cornerPathTexture);
					sprite.flip(true, false);
					sprite.setPosition(i * cWidth, rowCounter * cHeight);
					mainPath.add(sprite);
					break;
				case Constants.CornerPathSymbol_topRight:
					sprite = new Sprite(cornerPathTexture);
					sprite.setPosition(i * cWidth, rowCounter * cHeight);
					mainPath.add(sprite);
					break;
				case Constants.OpenPathSymbolX:
					sprite = new Sprite(openPathTexture);
					sprite.rotate(90);
					sprite.setPosition(i * oWidth, rowCounter * oHeight);
					mainPath.add(sprite);
					break;
				case Constants.OpenPathSymbolY:
					sprite = new Sprite(openPathTexture);
					sprite.setPosition(i * oWidth, rowCounter * oHeight);
					mainPath.add(sprite);
					break;
				}
				
				
			}
			
			rowCounter++;
		}
	}

}
