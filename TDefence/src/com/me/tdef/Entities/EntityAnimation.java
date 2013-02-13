package com.me.tdef.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Niko
 *
 */
public class EntityAnimation {
	
	private int frame_cols;
	private int frame_rows;
	
	private float animTimer;
	
	private Animation animation;
	private TextureRegion[] frames;
	private TextureRegion currentFrame;
	
	/**
	 * Returns the current frame of the animation.
	 */
	public TextureRegion currentFrameRegion() {
		return currentFrame;
	}
	
	/**
	 * Returns the origin of the current frame of the animation.
	 */
	public Vector2 frameOrigin(){
		return new Vector2(currentFrame.getRegionWidth() / 2, currentFrame.getRegionHeight() / 2);
	}
	
	/** Set up a basic looping animation.
	 * @param spriteSheet The image file.
	 * @param animationSpeed The interval for changing frames in seconds.
	 */
	public EntityAnimation(Texture spriteSheet, float animationSpeed){
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 
				spriteSheet.getWidth() / frame_cols, 
				spriteSheet.getHeight() / frame_rows);
		
		frames = new TextureRegion[frame_cols * frame_rows];
		
		int index = 0;
		
		for(int i = 0; i < frame_rows; i++)
			for(int j = 0; j < frame_cols; j++)
			{
				frames[index++] = tmp[i][j];
			}
		
		animation = new Animation(animationSpeed, frames);
		animTimer = 0f;
	}
	
	/** Main update method. Required to call in the main game loop.
	 * @param deltaTime game deltatime required for animation timers.
	 */
	public void update(float deltaTime){
		animTimer += deltaTime;
		currentFrame = animation.getKeyFrame(animTimer, true);
	}

}
