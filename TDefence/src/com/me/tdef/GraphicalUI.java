package com.me.tdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GraphicalUI {
	
	private Stage stage;

	public void create() {
		stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		TextureRegion upRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.buttonUpTexture)));
		TextureRegion downRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.buttonDownTexture)));
		BitmapFont buttonFont = new BitmapFont();
		
		TextButtonStyle style = new TextButtonStyle();
		
		style.up = new TextureRegionDrawable(upRegion);
		style.down = new TextureRegionDrawable(downRegion);
		style.font = buttonFont;
		
		TextButton button1 = new TextButton("Button 1", style);
		table.add(button1);
		
		TextButton button2 = new TextButton("Button 2", style);
		table.add(button2);
		
	}
	
	public void render() {
		stage.act();
		stage.draw();
		
		Table.drawDebug(stage);
	}
	
	public void dispose() {
		stage.dispose();
	}

}
