package com.me.tdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GraphicalUI {
	
	private Stage stage;
	private TextureRegion buttonUpTexture;
	private TextureRegion buttonDownTexture;
	
	public void create() {
		stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		buttonUpTexture = new TextureRegion(new Texture(Gdx.files.internal(Constants.buttonUpTexture)));
		buttonDownTexture = new TextureRegion(new Texture(Gdx.files.internal(Constants.buttonDownTexture)));
		
		LabelStyle labelStyle = new LabelStyle();
		BitmapFont bFont = new BitmapFont();
		TextButtonStyle bStyle = new TextButtonStyle();
		
		bStyle.font = bFont;
		bStyle.up = new TextureRegionDrawable(buttonUpTexture);
		bStyle.down = new TextureRegionDrawable(buttonDownTexture);
		labelStyle.font = bFont;
		
		TextButton buildButton = new TextButton("Build", bStyle);
		Label addressLabel = new Label("Credits: ", labelStyle);
		Label creditsLabel = new Label("0", labelStyle);
		
		table.add(buildButton).right().top();
		table.row();
		table.add(addressLabel).pad(5f).bottom().left();
		table.add(creditsLabel).pad(5f).bottom().left();
		
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
