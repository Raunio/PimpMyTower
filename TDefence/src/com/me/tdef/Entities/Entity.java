package com.me.tdef.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.tdef.Constants;

/** Base class for all game entities.
 * @author Niko
 *
 */
public abstract class Entity {
	
	protected Vector2 position;
	protected Vector2 velocity;
	protected float rotationVelocity;
	protected float acceleration;
	
	protected float rotation;
	protected float scaleX;
	protected float scaleY;
	
	protected EntityAnimation currentAnimation;
	
	protected Constants.RotationDirection rotationDirection;
	protected Vector2 facingPoint;
	protected float rotationMaxSpeed;
	protected float rotationAcceleration;
	
	public Rectangle boundingBox(){
		return new Rectangle(position.x, position.y, 
				currentAnimation.currentFrameRegion().getRegionWidth(), 
				currentAnimation.currentFrameRegion().getRegionHeight());
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(currentAnimation.currentFrameRegion(), position.x, position.y, 
				currentAnimation.frameOrigin().x, currentAnimation.frameOrigin().y, 
				boundingBox().width, boundingBox().height, scaleX, scaleY, rotation);
	}
	
	public void updateRotation(){
		float newRotation = rotation;
		
		if(facingPoint != null){
			Vector2 distance2D = new Vector2(facingPoint.x - position.x - currentAnimation.frameOrigin().x,
					facingPoint.y - position.y - currentAnimation.frameOrigin().y);
			
			newRotation = (float) Math.atan2(distance2D.y, distance2D.x);
		}
		
		float rotationDistance = rotation < newRotation ? newRotation - rotation : rotation - newRotation;
		
		if(rotationDistance > rotationAcceleration){
			
			if(rotation < newRotation){
				
				if(rotationDistance > 180f){
					
				}
				else{
					
				}
			}
			else{
				
			}
		}
	}
	

}
