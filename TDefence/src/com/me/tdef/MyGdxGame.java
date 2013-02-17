package com.me.tdef;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.tdef.Entities.Tower;
import com.me.tdef.Levels.GameLevel;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private GameLevel level;
	
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		//camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		level = new GameLevel();
		level.create();

	}

	@Override
	public void dispose() {
		batch.dispose();
		level.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		level.update(Gdx.graphics.getDeltaTime());
		level.handleInput(Gdx.input.getX(), Gdx.input.getY());
		for(Tower t : level.towers) {
			if(t.getRotation() != t.getTargetRotation()) {
				Gdx.app.log("rotation: ", "" + t.getRotation() + "\n");
				Gdx.app.log("targetRotation: ", "" + t.getTargetRotation() + "\n");
				Gdx.app.log("Turret position: ", "" + t.getPosition().x + "-" + t.getPosition().y + "\n");
				Gdx.app.log("Touch position: ", "" + Gdx.input.getX() + "-" + Gdx.input.getY() + "\n");
			}
		}
		//batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		level.render(batch);		
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
