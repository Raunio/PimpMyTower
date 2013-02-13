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
	protected float targetRotation;

	/**
	 * Gets the position of the entity.
	 */
	public Vector2 getPosition(){
		return position;
	}
	
	/**
	 * Gets the veclotiy of the entity.
	 */
	public Vector2 getVelocity(){
		return velocity;
	}
	
	/**
	 * Gets the rotation velocity of the entity.
	 */
	public float getRotationVelocity(){
		return rotationVelocity;
	}
	
	/**
	 * Gets the acceleration of the entity.
	 */
	public float getAcceleration(){
		return acceleration;
	}
	
	/**
	 * Gets the current rotation of the entity.
	 */
	public float getRotation(){
		return rotation;
	}
	
	/**
	 * Gets the x-axis scale of the entity.
	 */
	public float getScaleX(){
		return scaleX;
	}
	
	/**
	 * Gets the y-axis scale of the entity.
	 */
	public float getScaleY(){
		return scaleY;
	}
	
	/**
	 * Gets the current rotation direction of the entity.
	 */
	public Constants.RotationDirection getRotationDirection(){
		return rotationDirection;
	}
	
	/**
	 * Gets the facing point the entity is either facing currently or rotating towards.
	 */
	public Vector2 getFacingPoint(){
		return facingPoint;
	}
	
	/**
	 * Gets the maximun rotation speed of the entity.
	 */
	public float getRotationMaxSpeed(){
		return rotationMaxSpeed;
	}
	
	/**
	 * Gets the rotation acceleration of the entity.
	 */
	public float getRotationAcceleration(){
		return rotationAcceleration;
	}
	
	/**
	 * Gets the last known target rotation of the entity.
	 */
	public float getTargetRotation(){
		return targetRotation;
	}
	
	/**
	 * Applies all velocites to entity.
	 */
	public void applyVelocities(){
		position = new Vector2(position.x + velocity.x, position.y + velocity.y);
		rotation += rotationVelocity;
		
		if(rotation > 360)
			rotation = 0;
		else if(rotation < 0)
			rotation = 360;
	}
	
	/**
	 * Sets the velocity of the character..
	 */
	public void setVelocity(Vector2 newVelocity){
		this.velocity = newVelocity;
	}
	
	/**
	 * Sets the rotation velocity of the character.
	 */
	public void setRotationVelocity(float newVelocity){
		this.rotationVelocity = newVelocity;
	}
	
	/**
	 * Returns a rectangle the size of the current frame of the entitys animation. This is primarily used for collision detection.
	 */
	public Rectangle boundingBox(){
		return new Rectangle(position.x, position.y, 
				currentAnimation.currentFrameRegion().getRegionWidth(), 
				currentAnimation.currentFrameRegion().getRegionHeight());
	}
	
	/**
	 * Main draw method for the entity.
	 */
	public void draw(SpriteBatch batch){
		batch.draw(currentAnimation.currentFrameRegion(), position.x, position.y, 
				currentAnimation.frameOrigin().x, currentAnimation.frameOrigin().y, 
				boundingBox().width, boundingBox().height, scaleX, scaleY, rotation);
	}
	
	/**
	 * Calculates a new rotation value for the entity according to its facing point and sets its rotation direction accordingly.
	 */
	public void updateRotation(){
		float newRotation = rotation;
		
		if(facingPoint != null){
			Vector2 distance2D = new Vector2(facingPoint.x - position.x - currentAnimation.frameOrigin().x,
					facingPoint.y - position.y - currentAnimation.frameOrigin().y);
			
			newRotation = (float) Math.atan2(distance2D.y, distance2D.x) * 57.2957795f;
		}
		
		float rotationDistance = rotation < newRotation ? newRotation - rotation : rotation - newRotation;
		
		if(rotationDistance > rotationAcceleration){
			
			if(rotation < newRotation){
				
				if(rotationDistance > 180f){
					rotationDirection = Constants.RotationDirection.CounterClockwise;
				}
				else{
					rotationDirection = Constants.RotationDirection.Clockwise;
				}
			}
			
			else{
				
				if(rotationDistance > 180f){
					
					rotationDirection = Constants.RotationDirection.Clockwise;
				}
				
				else{
					rotationDirection = Constants.RotationDirection.CounterClockwise;
				}
			}
		}
		
		else{
			rotationDirection = Constants.RotationDirection.None;
		}
		
		targetRotation = newRotation;
	}
	

}
