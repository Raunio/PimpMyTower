package com.me.tdef;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.tdef.Levels.GameLevel;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private GameLevel level;
		
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		camera.position.set((w / 2) * 1.5f, (h / 2) * 1.5f, 0f);
		camera.zoom = 1.5f;
		batch = new SpriteBatch();
		
		level = new GameLevel();
		level.create(camera);

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
		
		camera.update();

		level.update(Gdx.graphics.getDeltaTime());
		level.handleInput(Gdx.input, camera.zoom);
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		level.render(batch, Gdx.graphics.getDeltaTime());

		batch.end();
		
		level.renderUI();
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
